package com.careyq.alive.operatelog.core.service;

import com.careyq.alive.core.util.AsyncUtils;
import com.careyq.alive.module.infra.api.LogApi;
import com.careyq.alive.module.infra.dto.OperateLogDTO;
import lombok.RequiredArgsConstructor;

/**
 * 操作日志 Framework 接口实现
 *
 * @author CareyQ
 */
@RequiredArgsConstructor
public class OperateLogFrameworkServiceImpl implements OperateLogFrameworkService {

    private final LogApi logApi;

    @Override
    public void createOperateLog(OperateLogDTO dto) {
        AsyncUtils.runAsync(() -> logApi.createOperateLog(dto));
    }
}
