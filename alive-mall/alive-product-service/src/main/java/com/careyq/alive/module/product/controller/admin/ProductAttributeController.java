package com.careyq.alive.module.product.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.core.domain.EntryVO;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.core.enums.IEnum;
import com.careyq.alive.module.product.dto.*;
import com.careyq.alive.module.product.enums.AttributeTypeEnum;
import com.careyq.alive.module.product.service.ProductAttributeGroupService;
import com.careyq.alive.module.product.service.ProductAttributeService;
import com.careyq.alive.module.product.service.ProductAttributeValueService;
import com.careyq.alive.module.product.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    private final ProductAttributeService attributeService;
    private final ProductAttributeGroupService attributeGroupService;
    private final ProductAttributeValueService attributeValueService;

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

    @GetMapping("/group/list")
    @Operation(summary = "获取商品属性分组列表")
    public Result<List<EntryVO>> getAttributeGroupList(@RequestParam Long categoryId) {
        return Result.ok(attributeGroupService.getAttributeGroupList(categoryId));
    }

    @GetMapping("/enums")
    @Operation(summary = "获取商品属性相关枚举")
    public Result<Map<String, Object>> getAttributeEnums() {
        Map<String, Object> res = Map.of(
                "attrType", IEnum.toEntry(AttributeTypeEnum.class),
                "group", attributeGroupService.list().stream().map(e -> new EntryVO(e.getId(), e.getName())).toList());
        return Result.ok(res);
    }

    @PostMapping("/param/save")
    @Operation(summary = "保存商品属性参数")
    public Result<Long> saveAttributeParam(@Validated @RequestBody ProductAttributeParamDTO dto) {
        return Result.ok(attributeService.saveAttributeParam(dto));
    }

    @PostMapping("/spec/save")
    @Operation(summary = "保存商品属性规格")
    public Result<Long> saveAttributeSpec(@Validated @RequestBody ProductAttributeSpecDTO dto) {
        return Result.ok(attributeService.saveAttributeSpec(dto));
    }

    @PostMapping("/page")
    @Operation(summary = "获取商品属性分页")
    public Result<IPage<ProductAttributePageVO>> getAttributePage(@Validated @RequestBody ProductAttributePageDTO dto) {
        return Result.ok(attributeService.getAttributePage(dto));
    }

    @GetMapping("/detail")
    @Operation(summary = "获取商品属性详情")
    public Result<ProductAttributeVO> getAttributeDetail(@RequestParam Long id) {
        return Result.ok(attributeService.getAttributeDetail(id));
    }

    @DeleteMapping("/del")
    @Operation(summary = "删除商品属性")
    public Result<Boolean> delAttribute(@RequestParam Long id) {
        attributeService.delAttribute(id);
        return Result.ok(true);
    }

    @GetMapping("/list")
    @Operation(summary = "获取商品属性列表")
    public Result<List<ProductAttributeListVO>> getAttributeList(@RequestParam Long categoryId) {
        return Result.ok(attributeService.getAttributeList(categoryId));
    }

    @PostMapping("/value/save")
    @Operation(summary = "保存商品属性值")
    public Result<Long> saveAttributeValue(@Validated @RequestBody ProductAttributeValueDTO dto) {
        return Result.ok(attributeValueService.saveAttributeValue(dto));
    }

    @DeleteMapping("/value/del")
    @Operation(summary = "删除商品属性值")
    public Result<Boolean> delAttributeValue(@RequestParam Long id) {
        return Result.ok(attributeValueService.removeById(id));
    }
}
