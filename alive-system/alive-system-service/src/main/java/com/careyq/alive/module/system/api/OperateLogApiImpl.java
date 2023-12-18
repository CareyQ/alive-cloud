package com.careyq.alive.module.system.api;

import com.careyq.alive.module.system.service.OperateLogService;
import com.careyq.alive.module.system.dto.OperateLogDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 操作日志 API 实现
 *
 * @author CareyQ
 */
@Service
@Validated
@AllArgsConstructor
public class OperateLogApiImpl implements OperateLogApi {

    private final OperateLogService operateLogService;

    @Override
    public void createOperateLog(OperateLogDTO dto) {
        operateLogService.crateOperateLog(dto);
    }
}
