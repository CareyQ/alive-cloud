package com.careyq.alive.module.product.rpc;

import com.careyq.alive.search.api.EsProductApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * RPC 配置类，用于引入 RPC 服务
 *
 * @author CareyQ
 */
@Configuration(proxyBeanMethods = false)
@EnableFeignClients(clients = {EsProductApi.class})
public class RpcConfiguration {
}
