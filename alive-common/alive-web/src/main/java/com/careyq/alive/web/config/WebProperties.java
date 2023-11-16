package com.careyq.alive.web.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * web 属性
 *
 * @author CareyQ
 * @since 2023-11-16
 */
@Data
@Validated
@ConfigurationProperties(prefix = "alive.web")
public class WebProperties {

    @NotNull(message = "APP API 不能为空")
    private API appApi = new API("/api-app", "**.controller.app.**");

    @NotNull(message = "ADMIN API 不能为空")
    private API adminApi = new API("/api-admin", "**.controller.admin.**");

    @Data
    @Valid
    @NoArgsConstructor
    @AllArgsConstructor
    public static class API {

        /**
         * API 前缀
         */
        @NotEmpty(message = "API 前缀不能为空")
        private String prefix;

        /**
         * Controller 所在包的 Ant 路径规则
         */
        @NotEmpty(message = "Controller 所在包不能为空")
        private String controller;
    }
}
