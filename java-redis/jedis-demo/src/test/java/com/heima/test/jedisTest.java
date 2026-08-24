package com.heima.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.Map;

public class jedisTest {
    private Jedis jedis;

    @BeforeEach
    void setUp() {
        //1建立连接
//        jedis = new Jedis("192.168.100.128",6379);
        jedis = JedisConnectionFactory.getJedis();
        //2设置密码
        jedis.auth("123321");
        //3选择数据库
        jedis.select(0);
    }

//    @Test
//    void testJedis() {
//        //存入数据
//        String result = jedis.set("name","虎哥");
//        System.out.println("result:"+result);
//        //取出数据
//        String name = jedis.get("name");
//        System.out.println("name:"+name);
//    }
    @Test
    void testJedis2() {
        //插入hash数据
        jedis.hset("user:1","name","jack");
        jedis.hset("user:1","age","18");
        //获取
        Map<String,String> user = jedis.hgetAll("user:1");
        System.out.println("user:"+user);
    }

    @AfterEach
    void tearDown() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
