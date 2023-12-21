package com.careyq.alive.satoken.config;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.context.SaTokenContext;
import com.careyq.alive.satoken.core.service.SaTokenContextImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * Sa-Token 配置
 *
 * @author CareyQ
 */
@AutoConfiguration
public class SaTokenConfiguration {

    public static final String TOKEN_NAME = "Authorization";
    public static final String TOKEN_STYLE = "random-64";

    @Bean
    @Primary
    public SaTokenConfig getSaTokenConfigPrimary() {
        SaTokenConfig config = new SaTokenConfig();
        config.setTokenName(TOKEN_NAME);
        config.setTokenStyle(TOKEN_STYLE);
        config.setIsLog(true);
        return config;
    }

    @Bean
    @Primary
    public SaTokenContext getSaTokenContext() {
        return new SaTokenContextImpl();
    }
}
