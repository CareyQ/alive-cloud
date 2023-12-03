package com.careyq.alive.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.system.entity.Menu;
import com.careyq.alive.system.entity.Role;
import com.careyq.alive.system.entity.RoleMenu;
import com.careyq.alive.system.entity.RoleUser;
import com.careyq.alive.system.mapper.MenuMapper;
import com.careyq.alive.system.mapper.RoleMapper;
import com.careyq.alive.system.mapper.RoleMenuMapper;
import com.careyq.alive.system.mapper.RoleUserMapper;
import com.careyq.alive.system.service.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 权限服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final RoleMenuMapper roleMenuMapper;
    private final RoleUserMapper roleUserMapper;
    private final RoleMapper roleMapper;
    private final MenuMapper menuMapper;

    @Override
    public void assignRoleMenu(Long roleId, Set<Long> menuIds) {
        Set<Long> dbMenuIds = this.getMenuIdsByRole(roleId);
        Collection<Long> addMenuIds = CollUtils.subtract(menuIds, dbMenuIds);
        Collection<Long> delMenuIds = CollUtils.subtract(dbMenuIds, menuIds);

        if (CollUtils.isNotEmpty(addMenuIds)) {
            Db.saveBatch(CollUtils.convertList(addMenuIds, menuId -> {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
                return roleMenu;
            }));
        }

        if (CollUtils.isNotEmpty(delMenuIds)) {
            roleMenuMapper.delete(new LambdaQueryWrapper<RoleMenu>()
                    .in(RoleMenu::getMenuId, delMenuIds)
                    .eq(RoleMenu::getRoleId, roleId));
        }
    }

    @Override
    public Set<Long> getMenuIdsByRole(Long roleId) {
        return CollUtils.convertSet(roleMenuMapper.getByRole(List.of(roleId)), RoleMenu::getMenuId);
    }

    @Override
    public void assignRoleUser(Long userId, Set<Long> roleIds) {
        Set<Long> dbRoleIds = this.getRoleIdsByUser(userId);
        Collection<Long> addRoleIds = CollUtils.subtract(roleIds, dbRoleIds);
        Collection<Long> delRoleIds = CollUtils.subtract(dbRoleIds, roleIds);

        if (CollUtils.isNotEmpty(addRoleIds)) {
            Db.saveBatch(CollUtils.convertList(addRoleIds, roleId -> {
                RoleUser roleUser = new RoleUser();
                roleUser.setRoleId(roleId);
                roleUser.setUserId(userId);
                return roleUser;
            }));
        }

        if (CollUtils.isNotEmpty(delRoleIds)) {
            roleUserMapper.delete(new LambdaQueryWrapper<RoleUser>()
                    .in(RoleUser::getRoleId, delRoleIds)
                    .eq(RoleUser::getUserId, userId));
        }
    }

    @Override
    public Set<Long> getRoleIdsByUser(Long userId) {
        return CollUtils.convertSet(roleUserMapper.getByUser(userId), RoleUser::getRoleId);
    }

    @Override
    public List<String> getUserRole(Long userId) {
        List<RoleUser> roleUser = roleUserMapper.getRoleByUser(userId);
        List<Role> roles = roleMapper.getByIds(CollUtils.convertList(roleUser, RoleUser::getRoleId));
        return CollUtils.convertList(roles, Role::getCode);
    }

    @Override
    public List<String> getUserPermission(Long userId) {
        List<RoleUser> roleUser = roleUserMapper.getRoleByUser(userId);
        List<RoleMenu> roleMenus = roleMenuMapper.getByRole(CollUtils.convertList(roleUser, RoleUser::getRoleId));
        List<Menu> menus = menuMapper.getByIds(CollUtils.convertList(roleMenus, RoleMenu::getMenuId));
        return CollUtils.convertList(menus, Menu::getPermission);
    }
}
