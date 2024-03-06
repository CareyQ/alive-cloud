package com.careyq.alive.module.product.convert;

import com.careyq.alive.module.product.entity.ProductAttributeGroup;
import com.careyq.alive.module.product.entity.ProductBrand;
import com.careyq.alive.module.product.vo.ProductAttributeGroupPageVO;
import com.careyq.alive.module.product.vo.ProductBrandPageVO;
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
public interface ProductAttributeGroupConvert {

    ProductAttributeGroupConvert INSTANCE = Mappers.getMapper(ProductAttributeGroupConvert.class);

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
     * 品牌转换为分页 VO
     *
     * @param brand 品牌
     * @return VO
     */
    ProductBrandPageVO brandConvert(ProductBrand brand);
}
