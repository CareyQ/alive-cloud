package com.careyq.alive.module.product.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.core.domain.EntryVO;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.module.product.dto.ProductBrandDTO;
import com.careyq.alive.module.product.dto.ProductBrandPageDTO;
import com.careyq.alive.module.product.service.ProductBrandService;
import com.careyq.alive.module.product.vo.ProductBrandPageVO;
import com.careyq.alive.module.product.vo.ProductBrandVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品品牌
 *
 * @author CareyQ
 */
@RestController
@AllArgsConstructor
@RequestMapping("/product/brand")
@Tag(name = "管理后台 - 商品品牌")
public class ProductBrandController {

    private final ProductBrandService brandService;

    @PostMapping("/save")
    @Operation(summary = "保存商品品牌")
    public Result<Long> saveBrand(@Validated @RequestBody ProductBrandDTO dto) {
        return Result.ok(brandService.saveBrand(dto));
    }

    @PostMapping("/page")
    @Operation(summary = "获取商品品牌分页")
    public Result<IPage<ProductBrandPageVO>> getBrandPage(@Validated @RequestBody ProductBrandPageDTO dto) {
        return Result.ok(brandService.getBrandPage(dto));
    }

    @GetMapping("/detail")
    @Operation(summary = "获取商品品牌详情")
    public Result<ProductBrandVO> getBrandDetail(@RequestParam Long id) {
        return Result.ok(brandService.getBrandDetail(id));
    }

    @DeleteMapping("/del")
    @Operation(summary = "删除商品品牌")
    public Result<Boolean> delBrand(@RequestParam Long id) {
        brandService.delBrand(id);
        return Result.ok(true);
    }

    @GetMapping("/list")
    @Operation(summary = "获取商品品牌列表")
    public Result<List<EntryVO>> getBrandList() {
        return Result.ok(brandService.getBrandList());
    }
}
