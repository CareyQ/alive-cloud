package com.careyq.alive.web.config;

import com.careyq.alive.module.infra.api.LogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 日志 rpc 配置
 *
 * @author CareyQ
 */
@AutoConfiguration
@EnableFeignClients(clients = {LogApi.class})
public class LogRpcConfiguration {
}
