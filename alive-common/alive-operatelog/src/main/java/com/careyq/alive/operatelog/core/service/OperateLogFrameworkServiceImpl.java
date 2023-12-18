package com.careyq.alive.operatelog.core.service;

import com.careyq.alive.core.util.AsyncUtils;
import com.careyq.alive.module.system.api.OperateLogApi;
import com.careyq.alive.module.system.dto.OperateLogDTO;
import lombok.RequiredArgsConstructor;

/**
 * 操作日志 Framework 接口实现
 *
 * @author CareyQ
 */
@RequiredArgsConstructor
public class OperateLogFrameworkServiceImpl implements OperateLogFrameworkService {

    private final OperateLogApi operateLogApi;

    @Override
    public void createOperateLog(OperateLogDTO dto) {
        AsyncUtils.runAsync(() -> operateLogApi.createOperateLog(dto));
    }
}
