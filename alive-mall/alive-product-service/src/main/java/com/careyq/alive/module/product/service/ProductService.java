package com.careyq.alive.module.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.mybatis.core.service.IServiceX;
import com.careyq.alive.module.product.entity.Product;
import com.careyq.alive.module.product.dto.*;
import com.careyq.alive.module.product.vo.*;

/**
 * 商品信息 服务
 *
 * @author CareyQ
 */
public interface ProductService extends IServiceX<Product> {

    /**
     * 创建商品信息
     *
     * @param dto 商品信息
     * @return 编号
     */
    Long createProduct(ProductDTO dto);

    /**
     * 更新商品信息
     *
     * @param dto 商品信息
     * @return 编号
     */
    Long updateProduct(ProductDTO dto);

    /**
     * 获取商品信息分页
     *
     * @param dto 分页筛选项
     * @return 分页
     */
    IPage<ProductPageVO> getPage(ProductPageDTO dto);

    /**
     * 获取商品信息详情
     *
     * @param id 编号
     * @return 商品信息
     */
    ProductVO getDetail(Long id);

    /**
     * 删除商品信息
     *
     * @param id 编号
     */
    void del(Long id);

    /**
     * 更新商品状态
     *
     * @param id     商品编号
     * @param status 状态
     */
    void updateStatus(Long id, Integer status);
}
