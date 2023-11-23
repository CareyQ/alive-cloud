package com.careyq.alive.system.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.core.domain.EntryVO;
import com.careyq.alive.core.domain.R;
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
    public R<Long> saveRole(@Validated @RequestBody RoleVO req) {
        return R.ok(roleService.saveRole(req));
    }

    @PostMapping("/page")
    @Operation(summary = "获取角色分页")
    public R<IPage<RoleVO>> getRolePage(@Validated @RequestBody RolePageDTO dto) {
        return R.ok(roleService.getRolePage(dto));
    }

    @GetMapping("/detail")
    @Operation(summary = "获取角色详情")
    public R<RoleVO> getRoleDetail(@RequestParam Long id) {
        return R.ok(roleService.getRoleDetail(id));
    }

    @DeleteMapping("/del")
    @Operation(summary = "删除角色")
    public R<Boolean> delRole(@RequestParam Long id) {
        roleService.delRole(id);
        return R.ok(true);
    }

    @GetMapping("/list")
    @Operation(summary = "获取角色列表")
    public R<List<EntryVO>> getRoleSimpleList() {
        return R.ok(roleService.getRoleSimpleList());
    }

    @PutMapping("/default")
    @Operation(summary = "改变默认角色")
    public R<Boolean> changeDefault(@RequestParam Long id, @RequestParam Boolean isDefault) {
        roleService.changeDefault(id, isDefault);
        return R.ok();
    }
}
