package com.careyq.alive.module.product.convert;

import com.careyq.alive.module.product.dto.ProductSkuDTO;
import com.careyq.alive.module.product.entity.Product;
import com.careyq.alive.module.product.entity.ProductBrand;
import com.careyq.alive.module.product.entity.ProductCategory;
import com.careyq.alive.module.product.entity.ProductSku;
import com.careyq.alive.module.product.vo.ProductBrandPageVO;
import com.careyq.alive.module.product.vo.ProductCategoryVO;
import com.careyq.alive.module.product.vo.ProductPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
     * @param category 商品分类
     * @return VO
     */
    ProductCategoryVO categoryConvert(ProductCategory category);

    /**
     * 品牌转换为分页 VO
     *
     * @param brand 品牌
     * @return VO
     */
    ProductBrandPageVO brandConvert(ProductBrand brand);

    /**
     * 商品转换为分页 VO
     *
     * @param product 商品
     * @return VO
     */
    @Mapping(target = "categoryName", ignore = true)
    @Mapping(target = "brandName", ignore = true)
    ProductPageVO convert(Product product);

    /**
     * 商品 sku 转换
     *
     * @param sku sku
     * @return VO
     */
    ProductSkuDTO skuConvert(ProductSku sku);
}
