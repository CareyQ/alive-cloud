package com.careyq.alive.module.system.api;

import com.careyq.alive.module.system.dto.OperateLogDTO;
import org.springframework.validation.annotation.Validated;

/**
 * 操作日志 API 接口
 *
 * @author CareyQ
 */
public interface OperateLogApi {

    /**
     * 创建操作日志
     *
     * @param dto 日志信息
     */
    void createOperateLog(@Validated OperateLogDTO dto);
}
