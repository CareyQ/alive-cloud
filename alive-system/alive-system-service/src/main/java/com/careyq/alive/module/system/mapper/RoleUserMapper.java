package com.careyq.alive.module.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.alive.module.system.entity.RoleUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色关联用户 mapper
 *
 * @author CareyQ
 */
@Mapper
public interface RoleUserMapper extends BaseMapper<RoleUser> {

    /**
     * 根据角色 ID 删除角色绑定的用户
     *
     * @param roleId 角色 ID
     */
    default void delByRole(Long roleId) {
        this.delete(new LambdaQueryWrapper<RoleUser>().eq(RoleUser::getRoleId, roleId));
    }

    /**
     * 根据用户 ID 获取用户绑定的角色
     *
     * @param userId 用户 ID
     * @return 角色关联用户
     */

    default List<RoleUser> getByUser(Long userId) {
        return this.selectList(new LambdaQueryWrapper<RoleUser>().eq(RoleUser::getUserId, userId));
    }

}
