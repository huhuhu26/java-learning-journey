package com.hmdp.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hmdp.entity.Shop;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.hmdp.utils.RedisConstants.CACHE_NULL_TTL;
import static com.hmdp.utils.RedisConstants.CACHE_SHOP_TTL;

@Slf4j
@Component
public class CacheClient {

    private final StringRedisTemplate stringRedisTemplate;

    public CacheClient(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void set(String key, Object value, Long time, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), time, unit);
    }

    //线程池
    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);

    public void setWithLogicalExpire(String key, Object value, Long time, TimeUnit unit) {
        // 设置逻辑过期
        RedisData redisData = new RedisData();
        redisData.setData(value);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));
        // 注意：逻辑过期方案不设置物理TTL，key永久存在，靠 expireTime 字段判断是否过期
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisData));
    }

    public <R, ID> R queryWithPassThrough(
            String keyPrefix, ID id, Class<R> type, Function<ID, R> dbFallback, Long time, TimeUnit unit) {
        String key = keyPrefix + id;
        //从redis查询
        String shopJson = stringRedisTemplate.opsForValue().get(key);
        //判断是否存在
        if (StrUtil.isNotBlank(shopJson)) {
            R r = JSONUtil.toBean(shopJson,type);
            return r;
        }
        //判断是否为空
        if (shopJson != null) {
            return null;
        }
        //不存在查询数据库
        R r = dbFallback.apply(id);
        if(r == null){
            //将空值存入redis
            stringRedisTemplate.opsForValue().set(key,"",CACHE_NULL_TTL, TimeUnit.MINUTES);
            //反回错误信息
            return null;
        }
        //存在存入redis
        this.set(key, r, time, unit);
        return r;
    }

    public <R, ID> R queryWithLogicalExpire(String keyPrefix, ID id, Class<R> type, Function<ID, R> dbFallback,Long time, TimeUnit unit) {
        String key = keyPrefix + id;
        //从redis查询
        String json = stringRedisTemplate.opsForValue().get(key);
        //判断是否存在
        if (StrUtil.isBlank(json)) {
            return null;
        }
        //4命中缓存，反序列化json
        RedisData redisData = JSONUtil.toBean(json, RedisData.class);
        R r = JSONUtil.toBean((JSONObject) redisData.getData(), type);
        //5判断是否过期
        if (redisData.getExpireTime().isAfter(LocalDateTime.now())) {
            //未过期，直接返回店铺信息
            return r;
        }
        //5.1过期，缓存重建
        //6.缓存重建
        //6.1获取互斥锁
        String lockKey = RedisConstants.LOCK_SHOP_KEY + id;
        boolean isLocked = tryLock(lockKey);
        //6.2判断获取锁是否成功
        if (isLocked) {
            //6.3获取锁成功，开新线程进行缓存重建
            CACHE_REBUILD_EXECUTOR.submit(() -> {
                try {
                    //6.4根据id查询数据库
                    R r1 = dbFallback.apply(id);
                    this.setWithLogicalExpire(key, r1, time, unit);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    //9.释放锁
                    unlock(lockKey);
                }
            });
        }
        return r;
    }

    public <R, ID> R queryWithMutex(String keyPrefix, ID id, Class<R> type, Function<ID, R> dbFallback,Long time, TimeUnit unit) {
        String key = keyPrefix + id;
        //1.从redis查询
        String json = stringRedisTemplate.opsForValue().get(key);
        //2.缓存命中，直接返回
        if (StrUtil.isNotBlank(json)) {
            return JSONUtil.toBean(json,type);
        }
        //3.命中空值（缓存穿透兜底），直接返回
        if (json != null) {
            return null;
        }
        //4.缓存未命中，尝试获取互斥锁
        String lockKey = RedisConstants.LOCK_SHOP_KEY + id;
        R r = null;
        boolean isLocked = false;
        try {
            //4.1获取互斥锁
            isLocked = tryLock(lockKey);
            //4.2获取锁失败，休眠后重试
            if (!isLocked) {
                Thread.sleep(50);
                return queryWithMutex(keyPrefix, id, type, dbFallback, time, unit);
            }
            //4.3获取锁成功，double-check：再查一次缓存（可能已被上一个线程重建）
            json = stringRedisTemplate.opsForValue().get(key);
            if (StrUtil.isNotBlank(json)) {
                return JSONUtil.toBean(json, type);
            }
            //5.缓存仍无，查询数据库
            r = dbFallback.apply(id);
            //模拟重建延迟
            Thread.sleep(200);
            if(r == null){
                //将空值存入redis，防止缓存穿透
                stringRedisTemplate.opsForValue().set(key,"",CACHE_NULL_TTL, TimeUnit.MINUTES);
                //返回错误信息（null 由调用方包装）
                return null;
            }
            //6.存在，写入redis
            stringRedisTemplate.opsForValue().set(key,JSONUtil.toJsonStr(r), time, unit);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            //7.只有抢到锁的线程才释放锁，避免误删他人锁
            if (isLocked) {
                unlock(lockKey);
            }
        }
        return r;
    }

    //尝试获取锁的方法
    private boolean tryLock(String key) {
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", 10, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(flag);
    }

    //释放锁的方法
    private void unlock(String key) {
        stringRedisTemplate.delete(key);
    }

}
