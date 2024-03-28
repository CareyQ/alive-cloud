package com.careyq.alive.module.product.convert;

import com.careyq.alive.module.product.entity.ProductAttribute;
import com.careyq.alive.module.product.vo.ProductAttributePageVO;
import com.careyq.alive.module.product.vo.ProductAttributeVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 商品属性相关转换器
 *
 * @author CareyQ
 */
@Mapper
public interface ProductAttributeConvert {

    ProductAttributeConvert INSTANCE = Mappers.getMapper(ProductAttributeConvert.class);

    /**
     * 属性转换为分页 VO
     *
     * @param attribute 属性
     * @return VO
     */
    ProductAttributePageVO convertToPageVo(ProductAttribute attribute);

    /**
     * 属性转换为 VO
     *
     * @param attribute 属性
     * @return VO
     */
    @Mapping(target = "value", ignore = true)
    ProductAttributeVO convertToVo(ProductAttribute attribute);
}
