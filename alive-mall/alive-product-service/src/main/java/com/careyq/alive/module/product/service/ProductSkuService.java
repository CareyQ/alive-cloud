package com.careyq.alive.module.product.service;

import com.careyq.alive.module.product.dto.ProductSkuDTO;
import com.careyq.alive.module.product.entity.ProductSku;
import com.careyq.alive.mybatis.core.service.IServiceX;

import java.util.List;

/**
 * 商品 SKU 信息 服务
 *
 * @author CareyQ
 */
public interface ProductSkuService extends IServiceX<ProductSku> {

    /**
     * 校验 SKU 信息
     *
     * @param skus SKU 信息
     */
    void validateSkus(List<ProductSkuDTO> skus);

    /**
     * 创建商品 SKU
     *
     * @param productId 商品编号
     * @param skus      SKU 信息
     */
    void createProductSku(Long productId, List<ProductSkuDTO> skus);

    /**
     * 更新商品 SKU
     *
     * @param productId 商品编号
     * @param skus      SKU 信息
     */
    void updateProductSku(Long productId, List<ProductSkuDTO> skus);
}
