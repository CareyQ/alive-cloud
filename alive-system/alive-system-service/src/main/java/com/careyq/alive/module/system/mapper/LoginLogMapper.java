package com.careyq.alive.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.alive.module.system.entity.LoginLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录日志 mapper
 *
 * @author CareyQ
 */
@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLog> {
}
