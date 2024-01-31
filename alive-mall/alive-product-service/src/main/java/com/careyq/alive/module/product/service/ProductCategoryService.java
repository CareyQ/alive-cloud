package com.careyq.alive.module.product.service;

import com.careyq.alive.module.product.dto.ProductCategoryDTO;
import com.careyq.alive.module.product.entity.ProductCategory;
import com.careyq.alive.module.product.vo.ProductCategoryVO;
import com.careyq.alive.mybatis.core.service.IServiceX;

import java.util.List;

/**
 * 商品分类 服务
 *
 * @author CareyQ
 */
public interface ProductCategoryService extends IServiceX<ProductCategory> {

    /**
     * 保存商品分类
     *
     * @param dto 商品分类
     * @return 编号
     */
    Long saveCategory(ProductCategoryDTO dto);

    /**
     * 获取商品分类列表
     *
     * @param name     分类名称
     * @param parentId 父级编号
     * @param status   状态
     * @return 分页
     */
    List<ProductCategoryVO> getCategoryList(String name, Long parentId, Integer status);

    /**
     * 获取商品分类详情
     *
     * @param id 编号
     * @return 商品分类
     */
    ProductCategoryVO getCategoryDetail(Long id);

    /**
     * 删除商品分类
     *
     * @param id 编号
     */
    void delCategory(Long id);
}
