package com.careyq.alive.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.alive.system.entity.RoleUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色关联用户 mapper
 *
 * @author CareyQ
 */
@Mapper
public interface RoleUserMapper extends BaseMapper<RoleUser> {

    default void delByRole(Long roleId) {
        this.delete(new LambdaQueryWrapper<RoleUser>().eq(RoleUser::getRoleId, roleId));
    }

    default List<RoleUser> getByUser(Long userId) {
        return this.selectList(new LambdaQueryWrapper<RoleUser>().eq(RoleUser::getUserId, userId));
    }

    default List<RoleUser> getRoleByUser(Long userId) {
        return this.selectList(new LambdaQueryWrapper<RoleUser>().eq(RoleUser::getUserId, userId));
    }
}
