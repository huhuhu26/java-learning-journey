package com.hmdp.utils;

import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class RedisIdWorker {

    private StringRedisTemplate stringRedisTemplate;

    public RedisIdWorker(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    // 起始的时间戳
    private static final long BEGIN_TIMESTAMP = 1640995200L;


    public long nextId(String keyPrefix) {
        // 1. 获取当前时间戳
        LocalDateTime now = LocalDateTime.now();
        long nowSeconds = now.toEpochSecond(ZoneOffset.UTC);
        long timestamp = nowSeconds - BEGIN_TIMESTAMP;
        // 2. 获取序列号
        //2.1获取当前日期
        String date = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        //2.2获取当前日期对应的计数器
        long count = stringRedisTemplate.opsForValue().increment("icr:" + keyPrefix + ":" + date);
        return timestamp << 32 | count;
    }
}
