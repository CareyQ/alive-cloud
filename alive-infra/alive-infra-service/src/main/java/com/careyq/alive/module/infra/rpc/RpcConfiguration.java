package com.careyq.alive.module.infra.rpc;

import com.careyq.alive.module.system.api.UserApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * RPC 配置类，用于引入 RPC 服务
 *
 * @author CareyQ
 */
@Configuration(proxyBeanMethods = false)
@EnableFeignClients(clients = {UserApi.class})
public class RpcConfiguration {
}
