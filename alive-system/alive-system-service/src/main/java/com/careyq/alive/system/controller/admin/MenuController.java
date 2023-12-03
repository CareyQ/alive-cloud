package com.careyq.alive.system.controller.admin;

import cn.hutool.core.lang.tree.Tree;
import com.careyq.alive.core.domain.R;
import com.careyq.alive.system.service.MenuService;
import com.careyq.alive.system.vo.MenuVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单管理
 *
 * @author CareyQ
 */
@Tag(name = "管理后台 - 菜单管理")
@RestController
@AllArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/tree")
    @Operation(summary = "获取菜单树")
    public R<List<Tree<Long>>> getMenuTree() {
        return R.ok(menuService.getMenuTree(false));
    }

    @GetMapping("/detail")
    @Operation(summary = "获取菜单详情")
    public R<MenuVO> getMenuDetail(@RequestParam Long id) {
        return R.ok(menuService.getMenuDetail(id));
    }

    @PostMapping("/save")
    @Operation(summary = "保存菜单")
    public R<Long> saveMenu(@Validated @RequestBody MenuVO menu) {
        return R.ok(menuService.saveMenu(menu));
    }

    @DeleteMapping("/del")
    @Operation(summary = "删除菜单")
    public R<Boolean> delMenu(@RequestParam Long id) {
        menuService.delMenu(id);
        return R.ok(true);
    }

    @GetMapping("/simple-tree")
    @Operation(summary = "获取简单菜单树，只包含启用")
    public R<List<Tree<Long>>> getMenuSimpleTree() {
        return R.ok(menuService.getMenuSimpleTree());
    }
}
