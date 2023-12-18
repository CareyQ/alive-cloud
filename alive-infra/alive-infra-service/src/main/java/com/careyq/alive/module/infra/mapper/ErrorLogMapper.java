package com.careyq.alive.module.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.alive.module.infra.entity.ErrorLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 错误日志 mapper
 *
 * @author CareyQ
 */
@Mapper
public interface ErrorLogMapper extends BaseMapper<ErrorLog> {
}
