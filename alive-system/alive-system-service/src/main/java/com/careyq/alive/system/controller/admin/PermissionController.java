package com.careyq.alive.system.controller.admin;

import com.careyq.alive.core.domain.Result;
import com.careyq.alive.system.dto.PermissionAssignDTO;
import com.careyq.alive.system.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * 权限管理
 *
 * @author CareyQ
 */
@Tag(name = "管理后台 - 权限管理")
@RestController
@AllArgsConstructor
@RequestMapping("/system/permission")
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping("/list-role-menu")
    @Operation(summary = "获取角色绑定的菜单")
    public Result<Set<Long>> getRoleMenuList(@RequestParam Long roleId) {
        return Result.ok(permissionService.getMenuIdsByRole(roleId));
    }

    @PostMapping("/assign-role-menu")
    @Operation(summary = "角色分配菜单")
    public Result<Boolean> assignRoleMenu(@Validated @RequestBody PermissionAssignDTO dto) {
        permissionService.assignRoleMenu(dto.getPrimaryId(), dto.getLinkIds());
        return Result.ok(true);
    }

    @GetMapping("/list-role-user")
    @Operation(summary = "获取用户绑定的角色")
    public Result<Set<Long>> getRoleUserList(@RequestParam Long userId) {
        return Result.ok(permissionService.getRoleIdsByUser(userId));
    }

    @PostMapping("/assign-role-user")
    @Operation(summary = "用户分配角色")
    public Result<Boolean> assignRoleUser(@Validated @RequestBody PermissionAssignDTO dto) {
        permissionService.assignRoleUser(dto.getPrimaryId(), dto.getLinkIds());
        return Result.ok(true);
    }
}
