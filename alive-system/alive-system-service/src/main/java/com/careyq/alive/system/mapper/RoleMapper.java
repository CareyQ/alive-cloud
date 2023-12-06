package com.careyq.alive.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.mybatis.core.mapper.LambdaQueryWrapperX;
import com.careyq.alive.system.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息 mapper
 *
 * @author CareyQ
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据 ID 获取角色列表
     *
     * @param ids 角色 ID
     * @return 角色列表
     */
    default List<Role> getByIds(List<Long> ids) {
        if (CollUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }
        return this.selectList(new LambdaQueryWrapperX<Role>().in(Role::getId, ids));
    }
}
