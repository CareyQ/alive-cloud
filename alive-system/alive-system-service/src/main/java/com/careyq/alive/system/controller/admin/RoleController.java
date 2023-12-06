package com.careyq.alive.system.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.core.domain.EntryVO;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.system.dto.RoleDTO;
import com.careyq.alive.system.dto.RolePageDTO;
import com.careyq.alive.system.service.RoleService;
import com.careyq.alive.system.vo.RoleVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理
 *
 * @author CareyQ
 */
@Tag(name = "管理后台 - 角色管理")
@RestController
@AllArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/save")
    @Operation(summary = "保存角色")
    public Result<Long> saveRole(@Validated @RequestBody RoleDTO dto) {
        return Result.ok(roleService.saveRole(dto));
    }

    @PostMapping("/page")
    @Operation(summary = "获取角色分页")
    public Result<IPage<RoleVO>> getRolePage(@Validated @RequestBody RolePageDTO dto) {
        return Result.ok(roleService.getRolePage(dto));
    }

    @GetMapping("/detail")
    @Operation(summary = "获取角色详情")
    public Result<RoleVO> getRoleDetail(@RequestParam Long id) {
        return Result.ok(roleService.getRoleDetail(id));
    }

    @DeleteMapping("/del")
    @Operation(summary = "删除角色")
    public Result<Boolean> delRole(@RequestParam Long id) {
        roleService.delRole(id);
        return Result.ok(true);
    }

    @GetMapping("/list")
    @Operation(summary = "获取角色列表")
    public Result<List<EntryVO>> getRoleSimpleList() {
        return Result.ok(roleService.getRoleSimpleList());
    }

    @PutMapping("/default")
    @Operation(summary = "改变默认角色")
    public Result<Boolean> changeDefault(@RequestParam Long id, @RequestParam Boolean isDefault) {
        roleService.changeDefault(id, isDefault);
        return Result.ok();
    }
}
