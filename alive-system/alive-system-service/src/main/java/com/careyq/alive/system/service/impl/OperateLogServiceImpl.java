package com.careyq.alive.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.careyq.alive.mybatis.core.service.impl.ServiceImplX;
import com.careyq.alive.system.convert.LogConvert;
import com.careyq.alive.system.dto.OperateLogDTO;
import com.careyq.alive.system.entity.OperateLog;
import com.careyq.alive.system.mapper.OperateLogMapper;
import com.careyq.alive.system.service.OperateLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 操作日志服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class OperateLogServiceImpl extends ServiceImplX<OperateLogMapper, OperateLog> implements OperateLogService {

    @Override
    public void crateOperateLog(OperateLogDTO dto) {
        OperateLog operateLog = LogConvert.INSTANCE.convert(dto);
        operateLog.setResultData(StrUtil.maxLength(operateLog.getResultData(), OperateLog.RESULT_MAX_LENGTH));
        operateLog.setJavaMethodArgs(StrUtil.maxLength(operateLog.getJavaMethodArgs(), OperateLog.JAVA_METHOD_ARGS_MAX_LENGTH));
        this.save(operateLog);
    }
}
