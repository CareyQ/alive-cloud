package com.careyq.alive.module.product.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.module.product.dto.*;
import com.careyq.alive.module.product.service.ProductAttributeGroupService;
import com.careyq.alive.module.product.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商品属性相关
 *
 * @author CareyQ
 */
@RestController
@AllArgsConstructor
@RequestMapping("/product/attribute")
@Tag(name = "管理后台 - 商品属性相关")
public class ProductAttributeController {

    private final ProductAttributeGroupService attributeGroupService;

    @PostMapping("/group/save")
    @Operation(summary = "保存商品属性分组")
    public Result<Long> saveAttributeGroup(@Validated @RequestBody ProductAttributeGroupDTO dto) {
        return Result.ok(attributeGroupService.saveAttributeGroup(dto));
    }

    @PostMapping("/group/page")
    @Operation(summary = "获取商品属性分组分页")
    public Result<IPage<ProductAttributeGroupPageVO>> getAttributeGroupPage(@Validated @RequestBody ProductAttributeGroupPageDTO dto) {
        return Result.ok(attributeGroupService.getAttributeGroupPage(dto));
    }

    @GetMapping("/group/detail")
    @Operation(summary = "获取商品属性分组详情")
    public Result<ProductAttributeGroupVO> getAttributeGroupDetail(@RequestParam Long id) {
        return Result.ok(attributeGroupService.getAttributeGroupDetail(id));
    }

    @DeleteMapping("/group/del")
    @Operation(summary = "删除商品属性分组")
    public Result<Boolean> delAttributeGroup(@RequestParam Long id) {
        attributeGroupService.delAttributeGroup(id);
        return Result.ok(true);
    }
}
