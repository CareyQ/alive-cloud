package com.careyq.alive.module.product.convert;

import com.careyq.alive.module.product.entity.ProductAttribute;
import com.careyq.alive.module.product.entity.ProductAttributeGroup;
import com.careyq.alive.module.product.vo.ProductAttributeGroupPageVO;
import com.careyq.alive.module.product.vo.ProductAttributePageVO;
import com.careyq.alive.module.product.vo.ProductAttributeVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Map;

/**
 * 商品属性相关转换器
 *
 * @author CareyQ
 */
@Mapper
public interface ProductAttributeConvert {

    ProductAttributeConvert INSTANCE = Mappers.getMapper(ProductAttributeConvert.class);

    /**
     * 商品属性分组转换为分页 VO
     *
     * @param group 商品属性分组
     * @return VO
     */
    @Mapping(target = "categoryName", ignore = true)
    ProductAttributeGroupPageVO groupConvert(ProductAttributeGroup group);

    /**
     * 商品属性分组转换为分页 VO
     *
     * @param group       商品属性分组
     * @param categoryMap 商品分类
     * @return VO
     */
    default ProductAttributeGroupPageVO groupConvert(ProductAttributeGroup group, Map<Long, String> categoryMap) {
        ProductAttributeGroupPageVO vo = groupConvert(group);
        vo.setCategoryName(categoryMap.get(group.getCategoryId()));
        return vo;
    }

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
