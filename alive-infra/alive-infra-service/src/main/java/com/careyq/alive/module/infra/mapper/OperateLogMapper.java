package com.careyq.alive.module.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.alive.module.infra.entity.OperateLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志 mapper
 *
 * @author CareyQ
 */
@Mapper
public interface OperateLogMapper extends BaseMapper<OperateLog> {
}
