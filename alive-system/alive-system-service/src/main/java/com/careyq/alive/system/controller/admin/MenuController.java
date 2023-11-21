package com.careyq.alive.system.controller.admin;

import cn.hutool.core.lang.tree.Tree;
import com.careyq.alive.core.domain.EntryVO;
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
 * @since 2023-11-19
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
        return R.ok(menuService.getMenuTree(null, false));
    }

    @GetMapping("/simple-list")
    @Operation(summary = "获取指定类型的简单菜单列表")
    public R<List<EntryVO>> getMenuTree(@RequestParam Integer type) {
        return R.ok(menuService.getListByType(type));
    }

    @GetMapping("/detail")
    @Operation(summary = "获取菜单详情")
    public R<MenuVO> getMenuDetail(@RequestParam Long id) {
        return R.ok(menuService.getMenuDetail(id));
    }

    @PostMapping("/save")
    @Operation(summary = "保存菜单")
    public R<Long> getMenuDetail(@Validated @RequestBody MenuVO menu) {
        return R.ok(menuService.saveMenu(menu));
    }
}
