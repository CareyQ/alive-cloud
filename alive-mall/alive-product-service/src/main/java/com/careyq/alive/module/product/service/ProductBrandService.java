package com.careyq.alive.module.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.mybatis.core.service.IServiceX;
import com.careyq.alive.module.product.entity.ProductBrand;
import com.careyq.alive.module.product.dto.*;
import com.careyq.alive.module.product.vo.*;

/**
 * 商品品牌 服务
 *
 * @author CareyQ
 */
public interface ProductBrandService extends IServiceX<ProductBrand> {

    /**
     * 保存商品品牌
     *
     * @param dto 商品品牌
     * @return 编号
     */
    Long saveBrand(ProductBrandDTO dto);

    /**
     * 获取商品品牌分页
     *
     * @param dto 分页筛选项
     * @return 分页
     */
    IPage<ProductBrandPageVO> getBrandPage(ProductBrandPageDTO dto);

    /**
     * 获取商品品牌详情
     *
     * @param id 编号
     * @return 商品品牌
     */
    ProductBrandVO getBrandDetail(Long id);

    /**
     * 删除商品品牌
     *
     * @param id 编号
     */
    void delBrand(Long id);
}
