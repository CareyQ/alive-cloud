package com.careyq.alive.module.infra.api;


import com.careyq.alive.module.infra.dto.ErrorLogDTO;
import com.careyq.alive.module.infra.dto.LoginLogDTO;
import com.careyq.alive.module.infra.dto.OperateLogDTO;
import com.careyq.alive.module.infra.service.LogService;
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
public class LogApiImpl implements LogApi {

    private final LogService logService;

    @Override
    public void createLoginLog(LoginLogDTO dto) {
        logService.createLoginLog(dto);
    }

    @Override
    public void createOperateLog(OperateLogDTO dto) {
        logService.createOperateLog(dto);
    }

    @Override
    public void createErrorLog(ErrorLogDTO dto) {
        logService.createErrorLog(dto);
    }
}
