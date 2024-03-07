package com.careyq.alive.module.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.module.product.dto.ProductAttributeGroupDTO;
import com.careyq.alive.module.product.dto.ProductAttributeGroupPageDTO;
import com.careyq.alive.module.product.entity.ProductAttributeGroup;
import com.careyq.alive.module.product.vo.ProductAttributeGroupPageVO;
import com.careyq.alive.module.product.vo.ProductAttributeGroupVO;
import com.careyq.alive.mybatis.core.service.IServiceX;

/**
 * 商品属性分组 服务
 *
 * @author CareyQ
 */
public interface ProductAttributeGroupService extends IServiceX<ProductAttributeGroup> {

    /**
     * 保存商品属性分组
     *
     * @param dto 商品属性分组
     * @return 编号
     */
    Long saveAttributeGroup(ProductAttributeGroupDTO dto);

    /**
     * 获取商品属性分组分页
     *
     * @param dto 分页筛选项
     * @return 分页
     */
    IPage<ProductAttributeGroupPageVO> getAttributeGroupPage(ProductAttributeGroupPageDTO dto);

    /**
     * 获取商品属性分组详情
     *
     * @param id 编号
     * @return 商品属性分组
     */
    ProductAttributeGroupVO getAttributeGroupDetail(Long id);

    /**
     * 删除商品属性分组
     *
     * @param id 编号
     */
    void delAttributeGroup(Long id);

}
