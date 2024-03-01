package com.careyq.alive.module.system.rpc;

import com.careyq.alive.module.infra.api.LogApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * RPC 配置类，用于引入 RPC 服务
 *
 * @author CareyQ
 */
@Configuration(proxyBeanMethods = false)
@EnableFeignClients(clients = {LogApi.class})
public class RpcConfiguration {
}
