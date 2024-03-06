package com.careyq.alive.module.product.controller.admin;

import cn.hutool.core.lang.tree.Tree;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.module.product.dto.ProductCategoryDTO;
import com.careyq.alive.module.product.service.ProductCategoryService;
import com.careyq.alive.module.product.vo.ProductCategoryVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类
 *
 * @author CareyQ
 */
@RestController
@AllArgsConstructor
@RequestMapping("/product/category")
@Tag(name = "管理后台 - 商品分类")
public class ProductCategoryController {

    private final ProductCategoryService categoryService;

    @PostMapping("/save")
    @Operation(summary = "保存商品分类")
    public Result<Long> saveCategory(@Validated @RequestBody ProductCategoryDTO dto) {
        return Result.ok(categoryService.saveCategory(dto));
    }

    @GetMapping("/list")
    @Operation(summary = "获取商品分类列表")
    public Result<List<ProductCategoryVO>> getCategoryList(@RequestParam(required = false) String name,
                                                           @RequestParam(required = false) Long parentId,
                                                           @RequestParam(required = false) Integer status) {
        return Result.ok(categoryService.getCategoryList(name, parentId, status));
    }

    @GetMapping("/detail")
    @Operation(summary = "获取商品分类详情")
    public Result<ProductCategoryVO> getCategoryDetail(@RequestParam Long id) {
        return Result.ok(categoryService.getCategoryDetail(id));
    }

    @DeleteMapping("/del")
    @Operation(summary = "删除商品分类")
    public Result<Boolean> delCategory(@RequestParam Long id) {
        categoryService.delCategory(id);
        return Result.ok(true);
    }

    @GetMapping("/tree")
    @Operation(summary = "获取商品分类树")
    public Result<List<Tree<Long>>> getCategoryTree() {
        return Result.ok(categoryService.getCategoryTree());
    }
}
