package com.cgz.conf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.cgz.user.controller.UserController;

public class JWTInterceptor implements HandlerInterceptor{
	Logger logger = LoggerFactory.getLogger(JWTInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //从请求头内获取token
        String token = request.getHeader("authorization");

        //验证令牌  如果令牌不正确会出现异常 被全局异常处理
        try {
        	JWTUtil.verify(token);
		} catch (Exception e) {
			logger.info("验证令牌捕获异常：" + e.toString());
		}
        return true;
    }
}
