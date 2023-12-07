package com.careyq.alive.operatelog.config;

import com.careyq.alive.system.api.OperateLogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 操作日志配置
 *
 * @author CareyQ
 */
@AutoConfiguration
@EnableFeignClients(clients = OperateLogApi.class)
public class OperateLogRpcConfiguration {
}
