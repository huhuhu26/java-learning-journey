package com.heima.jedis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class jedisConnectionFactory {
    private static final JedisPool jedisPool;

    static {
        //配置连接池
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置最大连接数
        jedisPoolConfig.setMaxTotal(8);
        //设置最大空闲连接数
        jedisPoolConfig.setMaxIdle(8);
        //设置最小空闲连接数
        jedisPoolConfig.setMinIdle(1);
        //等待时间
        jedisPoolConfig.setMaxWaitMillis(1000);

        //创建连接池对象
        jedisPool = new JedisPool(jedisPoolConfig, "192.168.100.128", 6379, 1000, "123321");

    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
