package com.careyq.alive.module.product.service;

import com.careyq.alive.module.product.dto.ProductAttributeValueDTO;
import com.careyq.alive.module.product.dto.ProductParamDTO;
import com.careyq.alive.module.product.entity.ProductAttributeValue;
import com.careyq.alive.module.product.entity.ProductSku;
import com.careyq.alive.mybatis.core.service.IServiceX;

import java.util.List;

/**
 * 商品属性值 服务
 *
 * @author CareyQ
 */
public interface ProductAttributeValueService extends IServiceX<ProductAttributeValue> {

    /**
     * 保存商品属性值
     *
     * @param dto 商品属性值
     * @return 属性值编号
     */
    Long saveAttributeValue(ProductAttributeValueDTO dto);

    /**
     * 保存商品属性参数
     *
     * @param productId 商品编号
     * @param param     商品属性参数
     */
    void createProductParam(Long productId, List<ProductParamDTO> param);

    /**
     * 更新商品属性参数
     *
     * @param productId 商品编号
     * @param param     商品属性参数
     */
    void updateProductParam(Long productId, List<ProductParamDTO> param);

    /**
     * 更新商品属性规格
     *
     * @param productId 商品编号
     * @param specs     属性规格
     */
    void updateProductSpec(Long productId, List<ProductSku.Spec> specs);
}
