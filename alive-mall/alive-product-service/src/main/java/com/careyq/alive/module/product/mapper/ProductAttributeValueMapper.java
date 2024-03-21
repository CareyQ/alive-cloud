package com.careyq.alive.module.product.mapper;

import com.careyq.alive.module.product.entity.ProductAttributeValue;
import com.careyq.alive.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品属性值 mapper
 *
 * @author CareyQ
 */
@Mapper
public interface ProductAttributeValueMapper extends BaseMapperX<ProductAttributeValue> {

    /**
     * 查询商品已存在的属性值
     *
     * @param productId 商品编号
     * @return 属性值
     */
    List<ProductAttributeValue> selectExistAttribute(@Param("productId") Long productId);
}