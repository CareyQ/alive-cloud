package com.careyq.alive.module.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.mybatis.core.service.IServiceX;
import com.careyq.alive.module.product.entity.ProductAttribute;
import com.careyq.alive.module.product.dto.*;
import com.careyq.alive.module.product.vo.*;

/**
 * 商品属性 服务
 *
 * @author CareyQ
 */
public interface ProductAttributeService extends IServiceX<ProductAttribute> {

    /**
     * 保存商品属性
     *
     * @param dto 商品属性
     * @return 编号
     */
    Long saveAttribute(ProductAttributeDTO dto);

    /**
     * 获取商品属性分页
     *
     * @param dto 分页筛选项
     * @return 分页
     */
    IPage<ProductAttributePageVO> getAttributePage(ProductAttributePageDTO dto);

    /**
     * 获取商品属性详情
     *
     * @param id 编号
     * @return 商品属性
     */
    ProductAttributeVO getAttributeDetail(Long id);

    /**
     * 删除商品属性
     *
     * @param id 编号
     */
    void delAttribute(Long id);
}
