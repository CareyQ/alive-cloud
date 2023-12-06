package com.careyq.alive.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.alive.mybatis.core.mapper.LambdaQueryWrapperX;
import com.careyq.alive.system.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色关联菜单 mapper
 *
 * @author CareyQ
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    default void delByRole(Long roleId) {
        this.delete(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, roleId));
    }

    default void delByMenu(Long menuId) {
        this.delete(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getMenuId, menuId));
    }

    default List<RoleMenu> getByRole(List<Long> roleIds) {
        return this.selectList(new LambdaQueryWrapperX<RoleMenu>().in(RoleMenu::getRoleId, roleIds));
    }

}
