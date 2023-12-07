package com.careyq.alive.operatelog.core.service;

import com.careyq.alive.system.dto.OperateLogDTO;

/**
 * 操作日志 Framework 接口
 *
 * @author CareyQ
 */
public interface OperateLogFrameworkService {

    /**
     * 记录操作日志
     *
     * @param dto 日志信息
     */
    void createOperateLog(OperateLogDTO dto);
}
