package com.ithuhu;

import com.ithuhu.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisDemoApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

//    @Test
//    void testString() {
//        redisTemplate.opsForValue().set("name","虎哥");
//        String name = (String) redisTemplate.opsForValue().get("name");
//        System.out.println("name = "+name);
//    }

    @Test
    void testSaveUser() {
        User user = new User("虎哥", 18);
        redisTemplate.opsForValue().set("user:100",user);
        //获取数据
        User user2 = (User) redisTemplate.opsForValue().get("user:100");
        System.out.println("user2 = "+user2);
    }
}
