package com.careyq.alive.module.product.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.module.product.dto.ProductDTO;
import com.careyq.alive.module.product.dto.ProductPageDTO;
import com.careyq.alive.module.product.service.ProductService;
import com.careyq.alive.module.product.vo.ProductPageVO;
import com.careyq.alive.module.product.vo.ProductVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商品信息
 *
 * @author CareyQ
 */
@RestController
@AllArgsConstructor
@RequestMapping("/product/")
@Tag(name = "管理后台 - 商品信息")
public class ProductController {

    private final ProductService Service;

    @PostMapping("/create")
    @Operation(summary = "新增商品信息")
    public Result<Long> save(@Validated @RequestBody ProductDTO dto) {
        return Result.ok(Service.createProduct(dto));
    }

    @PostMapping("/page")
    @Operation(summary = "获取商品信息分页")
    public Result<IPage<ProductPageVO>> getPage(@Validated @RequestBody ProductPageDTO dto) {
        return Result.ok(Service.getPage(dto));
    }

    @GetMapping("/detail")
    @Operation(summary = "获取商品信息详情")
    public Result<ProductVO> getDetail(@RequestParam Long id) {
        return Result.ok(Service.getDetail(id));
    }

    @DeleteMapping("/del")
    @Operation(summary = "删除商品信息")
    public Result<Boolean> del(@RequestParam Long id) {
        Service.del(id);
        return Result.ok(true);
    }
}
