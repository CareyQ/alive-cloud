package com.careyq.alive.module.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.mybatis.core.query.LambdaQueryWrapperX;
import com.careyq.alive.module.system.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色关联菜单 mapper
 *
 * @author CareyQ
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 根据角色 ID 删除角色绑定的菜单
     *
     * @param roleId 角色 ID
     */
    default void delByRole(Long roleId) {
        this.delete(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, roleId));
    }

    /**
     * 根据菜单 ID 删除角色绑定的菜单
     *
     * @param menuId 菜单 ID
     */
    default void delByMenu(Long menuId) {
        this.delete(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getMenuId, menuId));
    }

    /**
     * 根据角色 ID 获取角色绑定的菜单
     *
     * @param roleIds 角色 ID
     * @return 角色绑定的菜单
     */
    default List<RoleMenu> getByRole(List<Long> roleIds) {
        if (CollUtils.isEmpty(roleIds)) {
            return new ArrayList<>();
        }
        return this.selectList(new LambdaQueryWrapperX<RoleMenu>().in(RoleMenu::getRoleId, roleIds));
    }

}
