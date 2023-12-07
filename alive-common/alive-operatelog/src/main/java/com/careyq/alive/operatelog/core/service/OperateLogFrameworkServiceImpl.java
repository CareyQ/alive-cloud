package com.careyq.alive.operatelog.core.service;

import com.careyq.alive.system.api.OperateLogApi;
import com.careyq.alive.system.dto.OperateLogDTO;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

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
        CompletableFuture.runAsync(() -> operateLogApi.createOperateLog(dto));
    }
}
