package com.careyq.alive.module.product.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.core.domain.EntryVO;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.core.enums.IEnum;
import com.careyq.alive.module.product.dto.ProductAttributeDTO;
import com.careyq.alive.module.product.dto.ProductAttributeGroupDTO;
import com.careyq.alive.module.product.dto.ProductAttributeGroupPageDTO;
import com.careyq.alive.module.product.dto.ProductAttributePageDTO;
import com.careyq.alive.module.product.enums.AttributeTypeEnum;
import com.careyq.alive.module.product.service.ProductAttributeGroupService;
import com.careyq.alive.module.product.service.ProductAttributeService;
import com.careyq.alive.module.product.vo.ProductAttributeGroupPageVO;
import com.careyq.alive.module.product.vo.ProductAttributeGroupVO;
import com.careyq.alive.module.product.vo.ProductAttributePageVO;
import com.careyq.alive.module.product.vo.ProductAttributeVO;
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
    public Result<List<EntryVO>> getAttributeGroupList() {
        return Result.ok(attributeGroupService.getAttributeGroupList());
    }

    @GetMapping("/enums")
    @Operation(summary = "获取商品属性相关枚举")
    public Result<Map<String, Object>> getAttributeEnums() {
        Map<String, Object> res = Map.of(
                "attrType", IEnum.toEntry(AttributeTypeEnum.class),
                "group", attributeGroupService.list().stream().map(e -> new EntryVO(e.getId(), e.getName())).toList());
        return Result.ok(res);
    }

    @PostMapping("/save")
    @Operation(summary = "保存商品属性")
    public Result<Long> saveAttribute(@Validated @RequestBody ProductAttributeDTO dto) {
        return Result.ok(attributeService.saveAttribute(dto));
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
}
