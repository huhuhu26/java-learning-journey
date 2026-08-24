package com.ithuhu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ithuhu.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class RedisStringTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testString() {
        stringRedisTemplate.opsForValue().set("name","虎哥");
        String name = (String) stringRedisTemplate.opsForValue().get("name");
        System.out.println("name = "+name);
    }

    private static final ObjectMapper mapper = new ObjectMapper();
    @Test
    void testSaveUser() throws JsonProcessingException {
        User user = new User("虎哥", 18);
        //手动序列化
        String json = mapper.writeValueAsString(user);
        //写入数据
        stringRedisTemplate.opsForValue().set("user:200",json);
        //获取数据
        String jsonUser = (String) stringRedisTemplate.opsForValue().get("user:200");
        //手动反序列化
        User user2 = mapper.readValue(jsonUser, User.class);
        System.out.println("user2 = "+user2);
    }
}
