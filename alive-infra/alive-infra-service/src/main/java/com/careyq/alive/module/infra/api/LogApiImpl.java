package com.careyq.alive.module.infra.api;


import com.careyq.alive.module.infra.dto.LoginLogDTO;
import com.careyq.alive.module.infra.dto.OperateLogDTO;
import com.careyq.alive.module.infra.service.LoginLogService;
import com.careyq.alive.module.infra.service.OperateLogService;
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

    private final LoginLogService loginLogService;
    private final OperateLogService operateLogService;

    @Override
    public void createLoginLog(LoginLogDTO dto) {
        loginLogService.createLoginLog(dto);
    }

    @Override
    public void createOperateLog(OperateLogDTO dto) {
        operateLogService.crateOperateLog(dto);
    }
}
