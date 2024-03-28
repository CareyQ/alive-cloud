package com.careyq.alive.module.product.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.module.product.dto.ProductAttributePageDTO;
import com.careyq.alive.module.product.dto.ProductAttributeValueDTO;
import com.careyq.alive.module.product.service.ProductAttributeService;
import com.careyq.alive.module.product.service.ProductAttributeValueService;
import com.careyq.alive.module.product.vo.ProductAttributePageVO;
import com.careyq.alive.module.product.vo.ProductAttributeVO;
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

    private final ProductAttributeService attributeService;
    private final ProductAttributeValueService attributeValueService;

    @PostMapping("/save")
    @Operation(summary = "保存商品属性")
    public Result<Long> saveAttribute(@RequestParam String name) {
        return Result.ok(attributeService.saveAttribute(name));
    }

    @GetMapping("/page")
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
