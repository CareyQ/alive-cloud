package com.careyq.alive.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.alive.mybatis.core.mapper.LambdaQueryWrapperX;
import com.careyq.alive.system.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色信息 mapper
 *
 * @author CareyQ
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    default List<Role> getByIds(List<Long> ids) {
        return this.selectList(new LambdaQueryWrapperX<Role>().in(Role::getId, ids));
    }
}
