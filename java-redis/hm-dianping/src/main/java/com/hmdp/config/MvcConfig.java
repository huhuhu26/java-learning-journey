package com.hmdp.config;

import com.hmdp.utils.LoginIntercepter;
import com.hmdp.utils.RefreshTokenintercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginIntercepter(stringRedisTemplate))
                .excludePathPatterns("/user/login","/user/register","/user/code",
                        "/blog/hot","/blog/detail/*","/shop/**","/shop-type/**").order(1);
        registry.addInterceptor(new RefreshTokenintercepter(stringRedisTemplate)).order(0);
    }
}
