package com.itheima;

import com.ithema.UserService;
import org.junit.jupiter.api.Test;

public class UserServiceTest {
    @Test
    public void testGetAge(){
        UserService userService = new UserService();
        Integer age = userService.getAge("110101199001011234");
        System.out.println(age);
    }
}
