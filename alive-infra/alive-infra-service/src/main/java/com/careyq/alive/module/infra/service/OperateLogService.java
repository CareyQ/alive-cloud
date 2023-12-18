package com.careyq.alive.module.infra.service;

import com.careyq.alive.module.infra.entity.OperateLog;
import com.careyq.alive.module.infra.dto.OperateLogDTO;
import com.careyq.alive.mybatis.core.service.IServiceX;

/**
 * 操作日志服务
 *
 * @author CareyQ
 */
public interface OperateLogService extends IServiceX<OperateLog> {

    /**
     * 创建操作日志
     *
     * @param dto 操作日志
     */
    void crateOperateLog(OperateLogDTO dto);
}
