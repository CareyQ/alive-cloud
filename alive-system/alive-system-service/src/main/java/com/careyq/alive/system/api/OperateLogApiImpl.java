package com.careyq.alive.system.api;

import com.careyq.alive.core.domain.Result;
import com.careyq.alive.system.dto.OperateLogDTO;
import com.careyq.alive.system.service.OperateLogService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志 RPC
 *
 * @author CareyQ
 */
@Validated
@RestController
@AllArgsConstructor
public class OperateLogApiImpl implements OperateLogApi {

    private final OperateLogService operateLogService;

    @Override
    public Result<Boolean> createOperateLog(OperateLogDTO dto) {
        operateLogService.crateOperateLog(dto);
        return Result.ok(true);
    }
}
