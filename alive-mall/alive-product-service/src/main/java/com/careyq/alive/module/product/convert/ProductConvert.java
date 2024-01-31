package com.careyq.alive.module.product.convert;

import com.careyq.alive.module.product.entity.ProductCategory;
import com.careyq.alive.module.product.vo.ProductCategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 商品相关转换器
 *
 * @author CareyQ
 */
@Mapper
public interface ProductConvert {

    ProductConvert INSTANCE = Mappers.getMapper(ProductConvert.class);

    /**
     * 商品分类转换为 VO
     *
     * @param productCategory 商品分类
     * @return VO
     */
    ProductCategoryVO convert(ProductCategory productCategory);
}
