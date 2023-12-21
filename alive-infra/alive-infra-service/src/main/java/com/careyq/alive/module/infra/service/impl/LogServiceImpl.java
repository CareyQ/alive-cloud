package com.careyq.alive.module.infra.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.careyq.alive.core.constants.ResultCodeConstants;
import com.careyq.alive.module.infra.convert.LogConvert;
import com.careyq.alive.module.infra.dto.*;
import com.careyq.alive.module.infra.entity.ErrorLog;
import com.careyq.alive.module.infra.entity.LoginLog;
import com.careyq.alive.module.infra.entity.OperateLog;
import com.careyq.alive.module.infra.enums.ErrorLogProcessStatusEnum;
import com.careyq.alive.module.infra.mapper.ErrorLogMapper;
import com.careyq.alive.module.infra.mapper.LoginLogMapper;
import com.careyq.alive.module.infra.mapper.OperateLogMapper;
import com.careyq.alive.module.infra.service.LogService;
import com.careyq.alive.module.infra.vo.LoginLogPageVO;
import com.careyq.alive.module.infra.vo.OperateLogPageVO;
import com.careyq.alive.mybatis.core.mapper.LambdaQueryWrapperX;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 日志服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class LogServiceImpl implements LogService {

    private final LoginLogMapper loginLogMapper;
    private final ErrorLogMapper errorLogMapper;
    private final OperateLogMapper operateLogMapper;

    @Override
    public void createLoginLog(LoginLogDTO dto) {
        if (dto != null) {
            dto.setIpInfo(dto.getIpInfo().trim());
            LoginLog loginLog = LogConvert.INSTANCE.convert(dto);
            loginLogMapper.insert(loginLog);
        }
    }

    @Override
    public IPage<LoginLogPageVO> getLoginLogPage(LoginLogPageDTO dto) {
        IPage<LoginLog> page = loginLogMapper.selectPage(new Page<>(dto.getCurrent(), dto.getSize()),
                new LambdaQueryWrapperX<LoginLog>()
                        .likeIfPresent(LoginLog::getUsername, dto.getUsername())
                        .eqIfPresent(LoginLog::getIp, dto.getIp())
                        .dateTimeBetween(LoginLog::getCreateTime, dto.getStartDate(), dto.getEndDate())
                        .orderByDesc(LoginLog::getId));
        return page.convert(LogConvert.INSTANCE::convert);
    }

    @Override
    public void createOperateLog(OperateLogDTO dto) {
        OperateLog operateLog = LogConvert.INSTANCE.convert(dto);
        operateLog.setResultData(StrUtil.maxLength(operateLog.getResultData(), OperateLog.RESULT_MAX_LENGTH));
        operateLog.setJavaMethodArgs(StrUtil.maxLength(operateLog.getJavaMethodArgs(), OperateLog.JAVA_METHOD_ARGS_MAX_LENGTH));
        operateLogMapper.insert(operateLog);
    }

    @Override
    public void createErrorLog(ErrorLogDTO dto) {
        ErrorLog errorLog = LogConvert.INSTANCE.convert(dto);
        errorLog.setProcessStatus(ErrorLogProcessStatusEnum.INIT.getStatus());
        errorLogMapper.insert(errorLog);
    }

    @Override
    public IPage<OperateLogPageVO> getOperateLogPage(OperateLogPageDTO dto) {
        IPage<OperateLog> page = operateLogMapper.selectPage(new Page<>(dto.getCurrent(), dto.getSize()),
                new LambdaQueryWrapperX<OperateLog>()
                        .likeIfPresent(OperateLog::getUsername, dto.getUsername())
                        .likeIfPresent(OperateLog::getModule, dto.getModule())
                        .dateTimeBetween(OperateLog::getStartTime, dto.getStartDate(), dto.getEndDate())
                        .eq(Boolean.TRUE.equals(dto.getSuccess()), OperateLog::getResultCode, ResultCodeConstants.OK.code())
                        .gt(Boolean.FALSE.equals(dto.getSuccess()), OperateLog::getResultCode, ResultCodeConstants.OK.code())
                        .orderByDesc(OperateLog::getId));
        return page.convert(LogConvert.INSTANCE::convert);
    }
}
