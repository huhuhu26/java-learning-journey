package com.itheima.interceptor;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
//@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        //判断是否为登录请求
        if(requestURI.contains("/login")){
            log.info("登录请求，放行");
            return true;
        }

        //获取token
        String token = request.getHeader(" token");

        if(token == null||token.isEmpty()){
            log.info("令牌为空，响应401");
            response.setStatus(401);
            return false;
        }

        //验证令牌
        try{
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.info("令牌验证失败，响应401");
            response.setStatus(401);
            return false;
        }

        log.info("令牌验证通过，放行");
        return true;
    }
}
