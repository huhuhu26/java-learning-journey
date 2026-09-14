package com.hmdp.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.Shop;
import com.hmdp.mapper.ShopMapper;
import com.hmdp.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.utils.RedisConstants;
import com.hmdp.utils.RegexUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result queryById(Long id) {
        //缓存穿透（测试时二选一，注释掉另一个）
        //Shop shop = queryWithPassThrough(id);
        //缓存击穿：互斥锁方案
        Shop shop = queryWithMutex(id);
        if (shop == null) {
            return Result.fail("店铺不存在！");
        }
        return Result.ok(shop);
    }

    public Shop queryWithMutex(Long id) {
        String key = RedisConstants.CACHE_SHOP_KEY + id;
        //1.从redis查询
        String shopJson = stringRedisTemplate.opsForValue().get(key);
        //2.缓存命中，直接返回
        if (StrUtil.isNotBlank(shopJson)) {
            return JSONUtil.toBean(shopJson,Shop.class);
        }
        //3.命中空值（缓存穿透兜底），直接返回
        if (shopJson != null) {
            return null;
        }
        //4.缓存未命中，尝试获取互斥锁
        String lockKey = RedisConstants.LOCK_SHOP_KEY + id;
        Shop shop = null;
        boolean isLocked = false;
        try {
            //4.1获取互斥锁
            isLocked = tryLock(lockKey);
            //4.2获取锁失败，休眠后重试
            if (!isLocked) {
                Thread.sleep(50);
                return queryWithMutex(id);
            }
            //4.3获取锁成功，double-check：再查一次缓存（可能已被上一个线程重建）
            shopJson = stringRedisTemplate.opsForValue().get(key);
            if (StrUtil.isNotBlank(shopJson)) {
                return JSONUtil.toBean(shopJson,Shop.class);
            }
            //5.缓存仍无，查询数据库
            shop = getById(id);
            //模拟重建延迟
            Thread.sleep(200);
            if(shop == null){
                //将空值存入redis，防止缓存穿透
                stringRedisTemplate.opsForValue().set(key,"",CACHE_NULL_TTL, TimeUnit.MINUTES);
                //返回错误信息（null 由调用方包装）
                return null;
            }
            //6.存在，写入redis
            stringRedisTemplate.opsForValue().set(key,JSONUtil.toJsonStr(shop),CACHE_SHOP_TTL, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            //7.只有抢到锁的线程才释放锁，避免误删他人锁
            if (isLocked) {
                unlock(lockKey);
            }
        }
        return shop;
    }

    //获取店铺信息
    public Shop queryWithPassThrough(Long id) {
        String key = RedisConstants.CACHE_SHOP_KEY + id;
        //从redis查询
        String shopJson = stringRedisTemplate.opsForValue().get(key);
        //判断是否存在
        if (StrUtil.isNotBlank(shopJson)) {
            Shop shop = JSONUtil.toBean(shopJson,Shop.class);
            return shop;
        }
        //判断是否为空
        if (shopJson != null) {
            return null;
        }
        //不存在查询数据库
        Shop shop = getById(id);
        if(shop == null){
            //将空值存入redis
            stringRedisTemplate.opsForValue().set(key,"",CACHE_NULL_TTL, TimeUnit.MINUTES);
            //反回错误信息
            return null;
        }
        //存在存入redis
        stringRedisTemplate.opsForValue().set(key,JSONUtil.toJsonStr(shop),CACHE_SHOP_TTL, TimeUnit.MINUTES);
        return shop;
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

    @Override
    public Result update(Shop shop) {
        Long id = shop.getId();
        if (id == null) {
            return Result.fail("店铺id不能为空！");
        }
        //1更新数据库
        updateById(shop);
        //2删除缓存
        stringRedisTemplate.delete(CACHE_SHOP_KEY + id);
        return Result.ok();
    }
}
