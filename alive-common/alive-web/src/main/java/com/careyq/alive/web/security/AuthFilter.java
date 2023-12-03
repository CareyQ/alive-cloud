package com.careyq.alive.web.security;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 认证过滤
 *
 * @author CareyQ
 */
@AutoConfiguration
public class AuthFilter implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handler -> {
                    SaRouter.match("/**")
                            .notMatch("/auth/login")
                            .check(StpUtil::checkLogin);
                })).addPathPatterns("/**");
    }
}
