package com.syh1.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/api/login")) {
            // 放行登录请求
            return true;
        } else {
            // 其他请求需要拦截
            // TODO: 在这里添加拦截器的逻辑
            return false;
        }
    }
}
