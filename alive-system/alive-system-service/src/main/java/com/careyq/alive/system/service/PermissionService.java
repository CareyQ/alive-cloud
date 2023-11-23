package com.careyq.alive.system.service;

import java.util.Set;

/**
 * 权限服务
 *
 * @author CareyQ
 */
public interface PermissionService {

    /**
     * 角色分配菜单
     *
     * @param roleId  角色 ID
     * @param menuIds 菜单 ID
     */
    void assignRoleMenu(Long roleId, Set<Long> menuIds);

    /**
     * 获取角色绑定的菜单 ID
     *
     * @param roleId 角色 ID
     * @return 菜单 ID
     */
    Set<Long> getMenuIdsByRole(Long roleId);

    /**
     * 用户分配角色
     *
     * @param userId  用户 ID
     * @param roleIds 角色 ID
     */
    void assignRoleUser(Long userId, Set<Long> roleIds);

    /**
     * 获取用户绑定的角色 ID
     *
     * @param userId 用户 ID
     * @return 角色 ID
     */
    Set<Long> getRoleIdsByUser(Long userId);
}
