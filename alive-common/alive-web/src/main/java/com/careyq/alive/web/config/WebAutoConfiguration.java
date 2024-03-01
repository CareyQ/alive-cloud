package com.careyq.alive.web.config;

import cn.hutool.core.text.AntPathMatcher;
import com.careyq.alive.module.infra.api.LogApi;
import com.careyq.alive.web.filter.ApiRequestFilter;
import com.careyq.alive.web.handler.GlobalExceptionHandler;
import com.careyq.alive.web.util.WebUtils;
import jakarta.servlet.Filter;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
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
    public GlobalExceptionHandler globalExceptionHandler(LogApi logApi) {
        return new GlobalExceptionHandler(logApi);
    }

    @Bean
    @SuppressWarnings("InstantiationOfUtilityClass")
    public WebUtils webUtils(WebProperties webProperties) {
        return new WebUtils(webProperties);
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 设置访问源地址
        config.addAllowedOriginPattern("*");
        // 设置访问源请求头
        config.addAllowedHeader("*");
        // 设置访问源请求方法
        config.addAllowedMethod("*");
        // 创建 UrlBasedCorsConfigurationSource 对象
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 对接口配置跨域设置
        source.registerCorsConfiguration("/**", config);
        return createFilterBean(new CorsFilter(source), Integer.MIN_VALUE);
    }

    @Bean
    public FilterRegistrationBean<ApiRequestFilter> apiRequestFilter(WebProperties webProperties) {
        return createFilterBean(new ApiRequestFilter(webProperties), Integer.MIN_VALUE + 1);
    }
}
