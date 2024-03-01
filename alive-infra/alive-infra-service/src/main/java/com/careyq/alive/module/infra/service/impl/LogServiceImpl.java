package com.careyq.alive.module.infra.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.careyq.alive.core.constants.ResultCodeConstants;
import com.careyq.alive.core.exception.CustomException;
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
import com.careyq.alive.module.infra.vo.*;
import com.careyq.alive.module.system.api.UserApi;
import com.careyq.alive.mybatis.core.query.LambdaQueryWrapperX;
import com.careyq.alive.satoken.AuthHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.careyq.alive.module.infra.constants.InfraResultCode.ERROR_LOG_NOT_FOUND;
import static com.careyq.alive.module.infra.constants.InfraResultCode.ERROR_LOG_PROCESSED;

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
    private final UserApi userApi;

    @Override
    public void createLoginLog(LoginLogDTO dto) {
        if (dto != null) {
            dto.setIpInfo(dto.getIpInfo().trim());
            LoginLog loginLog = LogConvert.INSTANCE.convert(dto);
            loginLog.setTraceId("1");
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
                        .likeIfPresent(OperateLog::getNickname, dto.getNickname())
                        .likeIfPresent(OperateLog::getModule, dto.getModule())
                        .eqIfPresent(OperateLog::getType, dto.getType())
                        .dateTimeBetween(OperateLog::getStartTime, dto.getStartDate(), dto.getEndDate())
                        .eq(Boolean.TRUE.equals(dto.getSuccess()), OperateLog::getResultCode, ResultCodeConstants.OK.code())
                        .gt(Boolean.FALSE.equals(dto.getSuccess()), OperateLog::getResultCode, ResultCodeConstants.OK.code())
                        .orderByDesc(OperateLog::getId));
        return page.convert(LogConvert.INSTANCE::convert);
    }

    @Override
    public OperateLogVO getOperateLogDetail(Long id) {
        OperateLog operateLog = operateLogMapper.selectById(id);
        return LogConvert.INSTANCE.convertToVo(operateLog);
    }

    @Override
    public IPage<ErrorLogPageVO> getErrorLogPage(ErrorLogPageDTO dto) {
        IPage<ErrorLog> page = errorLogMapper.selectPage(new Page<>(dto.getCurrent(), dto.getSize()),
                new LambdaQueryWrapperX<ErrorLog>()
                        .likeIfPresent(ErrorLog::getNickname, dto.getNickname())
                        .likeIfPresent(ErrorLog::getRequestUrl, dto.getRequestUrl())
                        .eqIfPresent(ErrorLog::getUserType, dto.getUserType())
                        .eqIfPresent(ErrorLog::getProcessStatus, dto.getProcessStatus())
                        .dateTimeBetween(ErrorLog::getCreateTime, dto.getStartDate(), dto.getEndDate())
                        .orderByDesc(ErrorLog::getId));
        return page.convert(LogConvert.INSTANCE::convert);
    }

    @Override
    public ErrorLogVO getErrorLogDetail(Long id) {
        ErrorLog errorLog = errorLogMapper.selectById(id);
        ErrorLogVO vo = LogConvert.INSTANCE.convertToVo(errorLog);
        if (errorLog.getProcessUserId() != null) {
            vo.setProcessUsername(userApi.getNickname(errorLog.getProcessUserId()));
        }
        return vo;
    }

    @Override
    public void updateErrorProcessStatus(Long id, Integer processStatus) {
        ErrorLog errorLog = errorLogMapper.selectById(id);
        if (errorLog == null) {
            throw new CustomException(ERROR_LOG_NOT_FOUND);
        }
        if (!ErrorLogProcessStatusEnum.INIT.getStatus().equals(errorLog.getProcessStatus())) {
            throw new CustomException(ERROR_LOG_PROCESSED);
        }
        ErrorLog updateLog = new ErrorLog();
        updateLog.setProcessStatus(processStatus)
                .setProcessTime(LocalDateTime.now())
                .setProcessUserId(AuthHelper.getUserId())
                .setId(id);
        errorLogMapper.updateById(updateLog);
    }
}
