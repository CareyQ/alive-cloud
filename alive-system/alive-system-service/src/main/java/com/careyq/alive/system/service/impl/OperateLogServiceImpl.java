package com.careyq.alive.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.careyq.alive.mybatis.core.service.impl.ServiceImplX;
import com.careyq.alive.system.convert.LogConvert;
import com.careyq.alive.system.dto.OperateLogDTO;
import com.careyq.alive.system.entity.OperateLog;
import com.careyq.alive.system.mapper.OperateLogMapper;
import com.careyq.alive.system.service.OperateLogService;
import lombok.AllArgsConstructor;
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
        OperateLog log = LogConvert.INSTANCE.convert(dto);
        log.setResultData(StrUtil.maxLength(log.getResultData(), OperateLog.RESULT_MAX_LENGTH));
        log.setJavaMethodArgs(StrUtil.maxLength(log.getJavaMethodArgs(), OperateLog.JAVA_METHOD_ARGS_MAX_LENGTH));
        this.save(log);
    }
}
