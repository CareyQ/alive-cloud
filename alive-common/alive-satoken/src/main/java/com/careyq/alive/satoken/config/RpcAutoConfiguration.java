package com.careyq.alive.satoken.config;

import com.careyq.alive.satoken.rpc.RequestInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Sa-Token feign 配置
 *
 * @author CareyQ
 */
@AutoConfiguration
public class RpcAutoConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor();
    }
}
