package com.careyq.alive.module.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.module.product.convert.ProductAttributeConvert;
import com.careyq.alive.module.product.dto.ProductAttributeDTO;
import com.careyq.alive.module.product.dto.ProductAttributePageDTO;
import com.careyq.alive.module.product.entity.ProductAttribute;
import com.careyq.alive.module.product.entity.ProductAttributeGroup;
import com.careyq.alive.module.product.mapper.ProductAttributeMapper;
import com.careyq.alive.module.product.service.ProductAttributeGroupService;
import com.careyq.alive.module.product.service.ProductAttributeService;
import com.careyq.alive.module.product.vo.ProductAttributePageVO;
import com.careyq.alive.module.product.vo.ProductAttributeVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.careyq.alive.module.product.constants.ProductResultCode.ATTRIBUTE_GROUP_NOT_EXISTS;
import static com.careyq.alive.module.product.constants.ProductResultCode.ATTRIBUTE_NOT_EXISTS;

/**
 * 商品属性 服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttribute> implements ProductAttributeService {

    private ProductAttributeGroupService groupService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveAttribute(ProductAttributeDTO dto) {
        boolean exists = this.lambdaQueryX()
                .neIfPresent(ProductAttribute::getId, dto.getId())
                .eq(ProductAttribute::getGroupId, dto.getGroupId())
                .eq(ProductAttribute::getName, dto.getName())
                .exists();
        if (exists) {
            throw new CustomException();
        }
        if (dto.getId() != null) {
            this.checkDataExists(dto.getId());
        }
        ProductAttribute attribute = BeanUtil.copyProperties(dto, ProductAttribute.class);
        ProductAttributeGroup group = groupService.getById(dto.getGroupId());
        if (group == null) {
            throw new CustomException(ATTRIBUTE_GROUP_NOT_EXISTS);
        }
        attribute.setCategoryId(group.getCategoryId());
        this.saveOrUpdate(attribute);
        return attribute.getId();
    }

    @Override
    public IPage<ProductAttributePageVO> getAttributePage(ProductAttributePageDTO dto) {
        IPage<ProductAttribute> page = this.lambdaQueryX()
                .likeIfPresent(ProductAttribute::getName, dto.getName())
                .eq(ProductAttribute::getGroupId, dto.getGroupId())
                .eq(ProductAttribute::getType, dto.getType())
                .orderByDesc(ProductAttribute::getId)
                .page(new Page<>(dto.getCurrent(), dto.getSize()));
        if (page.getRecords().isEmpty()) {
            return new Page<>();
        }
        return page.convert(ProductAttributeConvert.INSTANCE::convertToPageVo);
    }

    @Override
    public ProductAttributeVO getAttributeDetail(Long id) {
        ProductAttribute data = this.checkDataExists(id);
        return BeanUtil.copyProperties(data, ProductAttributeVO.class);
    }

    @Override
    public void delAttribute(Long id) {
        this.checkDataExists(id);
        this.removeById(id);
    }

    /**
     * 校验商品属性是否存在
     *
     * @param id 编号
     * @return 商品属性
     */
    private ProductAttribute checkDataExists(Long id) {
        if (id == null) {
            return null;
        }
        ProductAttribute data = this.getById(id);
        if (data == null) {
            throw new CustomException(ATTRIBUTE_NOT_EXISTS);
        }
        return data;
    }

    @Override
    public List<ProductAttributeVO> getAttributeList(Long groupId, Integer type) {
        List<ProductAttribute> list = this.lambdaQuery()
                .eq(ProductAttribute::getGroupId, groupId)
                .eq(ProductAttribute::getType, type)
                .orderByAsc(ProductAttribute::getSort)
                .list();
        return CollUtils.convertList(list, ProductAttributeConvert.INSTANCE::convertToVo);
    }
}
