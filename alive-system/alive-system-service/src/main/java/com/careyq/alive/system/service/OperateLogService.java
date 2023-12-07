package com.careyq.alive.system.service;

import com.careyq.alive.mybatis.core.service.IServiceX;
import com.careyq.alive.system.dto.OperateLogDTO;
import com.careyq.alive.system.entity.OperateLog;

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
