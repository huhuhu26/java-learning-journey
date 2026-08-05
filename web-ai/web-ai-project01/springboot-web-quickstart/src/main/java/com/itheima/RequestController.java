package com.itheima;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/request")
public class RequestController {
    @RequestMapping("/request")
    public void request(HttpServletRequest request){
        // 获取请求方式
        String method = request.getMethod();

    }
}
