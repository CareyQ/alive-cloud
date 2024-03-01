package com.careyq.alive.web.config;

import cn.dev33.satoken.stp.StpUtil;
import com.careyq.alive.satoken.interceptor.AliveSaInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 权限安全配置
 *
 * @author CareyQ
 */
@AutoConfiguration
public class SecurityConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AliveSaInterceptor(handle -> StpUtil.checkLogin()))
                .addPathPatterns("/**");
    }
}
