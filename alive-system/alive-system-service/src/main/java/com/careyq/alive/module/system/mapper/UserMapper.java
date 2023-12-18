package com.careyq.alive.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.alive.module.system.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户 mapper
 *
 * @author CareyQ
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
