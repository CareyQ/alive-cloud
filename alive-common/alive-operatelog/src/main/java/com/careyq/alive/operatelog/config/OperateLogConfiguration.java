package com.careyq.alive.operatelog.config;

import com.careyq.alive.operatelog.core.aop.OperateLogAspect;
import com.careyq.alive.operatelog.core.service.OperateLogFrameworkService;
import com.careyq.alive.operatelog.core.service.OperateLogFrameworkServiceImpl;
import com.careyq.alive.system.api.OperateLogApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 操作日志配置
 *
 * @author CareyQ
 */
@AutoConfiguration
public class OperateLogConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect() {
        return new OperateLogAspect();
    }

    @Bean
    public OperateLogFrameworkService operateLogFrameworkService(@Qualifier("operateLogApiImpl") OperateLogApi operateLogApi) {
        return new OperateLogFrameworkServiceImpl(operateLogApi);
    }
}
