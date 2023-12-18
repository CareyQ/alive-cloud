package com.careyq.alive.module.infra.api;

import com.careyq.alive.module.infra.dto.ErrorLogDTO;
import com.careyq.alive.module.infra.dto.LoginLogDTO;
import com.careyq.alive.module.infra.dto.OperateLogDTO;
import org.springframework.validation.annotation.Validated;

/**
 * 日志 API 接口
 *
 * @author CareyQ
 */
public interface LogApi {

    /**
     * 创建登录日志
     *
     * @param dto 日志信息
     */
    void createLoginLog(@Validated LoginLogDTO dto);

    /**
     * 创建操作日志
     *
     * @param dto 日志信息
     */
    void createOperateLog(@Validated OperateLogDTO dto);

    /**
     * 创建错误日志
     *
     * @param dto 日志信息
     */
    void createErrorLog(@Validated ErrorLogDTO dto);
}
