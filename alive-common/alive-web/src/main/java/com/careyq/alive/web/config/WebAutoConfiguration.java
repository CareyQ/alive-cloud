package com.careyq.alive.web.config;

import cn.hutool.core.text.AntPathMatcher;
import com.careyq.alive.web.handler.GlobalExceptionHandler;
import com.careyq.alive.web.util.WebUtils;
import jakarta.servlet.Filter;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web 自动配置
 *
 * @author CareyQ
 */
@AutoConfiguration
@AllArgsConstructor
public class WebAutoConfiguration implements WebMvcConfigurer {

    private final WebProperties webProperties;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher antPathMatcher = new AntPathMatcher(".");
        configurer.addPathPrefix(webProperties.getAdminApi().getPrefix(), clazz -> clazz.isAnnotationPresent(RestController.class)
                && antPathMatcher.match(webProperties.getAdminApi().getController(), clazz.getPackage().getName()));
    }

    public static <T extends Filter> FilterRegistrationBean<T> createFilterBean(T filter, Integer order) {
        FilterRegistrationBean<T> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(order);
        return bean;
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    @SuppressWarnings("InstantiationOfUtilityClass")
    public WebUtils webUtils(WebProperties webProperties) {
        return new WebUtils(webProperties);
    }
}
