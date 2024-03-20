package com.careyq.alive.module.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.module.product.convert.ProductAttributeConvert;
import com.careyq.alive.module.product.dto.ProductAttributeParamDTO;
import com.careyq.alive.module.product.dto.ProductAttributePageDTO;
import com.careyq.alive.module.product.dto.ProductAttributeSpecDTO;
import com.careyq.alive.module.product.entity.ProductAttribute;
import com.careyq.alive.module.product.entity.ProductAttributeGroup;
import com.careyq.alive.module.product.enums.AttributeTypeEnum;
import com.careyq.alive.module.product.mapper.ProductAttributeMapper;
import com.careyq.alive.module.product.service.ProductAttributeGroupService;
import com.careyq.alive.module.product.service.ProductAttributeService;
import com.careyq.alive.module.product.vo.ProductAttributeListVO;
import com.careyq.alive.module.product.vo.ProductAttributePageVO;
import com.careyq.alive.module.product.vo.ProductAttributeVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.careyq.alive.module.product.constants.ProductResultCode.ATTRIBUTE_NOT_EXISTS;

/**
 * 商品属性 服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttribute> implements ProductAttributeService {

    private final ProductAttributeGroupService attributeGroupService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveAttributeParam(ProductAttributeParamDTO dto) {
        boolean exists = this.lambdaQueryX()
                .neIfPresent(ProductAttribute::getId, dto.getId())
                .eq(ProductAttribute::getGroupId, dto.getGroupId())
                .eq(ProductAttribute::getType, AttributeTypeEnum.PARAM.getCode())
                .eq(ProductAttribute::getName, dto.getName())
                .exists();
        if (exists) {
            throw new CustomException();
        }
        if (dto.getId() != null) {
            this.checkDataExists(dto.getId());
        }
        ProductAttribute attribute = BeanUtil.copyProperties(dto, ProductAttribute.class);
        attribute.setType(AttributeTypeEnum.PARAM.getCode());
        this.saveOrUpdate(attribute);
        return attribute.getId();
    }

    @Override
    public Long saveAttributeSpec(ProductAttributeSpecDTO dto) {
        ProductAttribute attribute = new ProductAttribute();
        attribute.setType(AttributeTypeEnum.SPEC.getCode())
                .setName(dto.getName())
                .setId(dto.getId());
        this.saveOrUpdate(attribute);
        return attribute.getId();
    }

    @Override
    public IPage<ProductAttributePageVO> getAttributePage(ProductAttributePageDTO dto) {
        IPage<ProductAttribute> page = this.lambdaQueryX()
                .likeIfPresent(ProductAttribute::getName, dto.getName())
                .eqIfPresent(ProductAttribute::getGroupId, dto.getGroupId())
                .eqIfPresent(ProductAttribute::getType, dto.getType())
                .orderByAsc(ProductAttribute::getSort)
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
    public List<ProductAttributeListVO> getAttributeList(Long categoryId) {
        List<ProductAttributeGroup> groups = attributeGroupService.lambdaQuery()
                .eq(ProductAttributeGroup::getCategoryId, categoryId)
                .orderByAsc(ProductAttributeGroup::getSort)
                .list();
        if (groups.isEmpty()) {
            return new ArrayList<>();
        }
        List<Long> groupIds = CollUtils.convertList(groups, ProductAttributeGroup::getId);
        List<ProductAttribute> attributes = this.lambdaQuery()
                .in(ProductAttribute::getGroupId, groupIds)
                .eq(ProductAttribute::getType, AttributeTypeEnum.PARAM.getCode())
                .orderByAsc(ProductAttribute::getSort)
                .list();
        if (attributes.isEmpty()) {
            return new ArrayList<>();
        }

        List<ProductAttributeListVO> res = new ArrayList<>();
        Map<Long, List<ProductAttribute>> attribueMap = CollUtils.convertMultiMap(attributes, ProductAttribute::getGroupId);
        for (ProductAttributeGroup group : groups) {
            List<ProductAttribute> attribute = attribueMap.get(group.getId());
            if (CollUtils.isEmpty(attribute)) {
                continue;
            }
            ProductAttributeListVO vo = new ProductAttributeListVO();
            vo.setGroupId(group.getId());
            vo.setGroupName(group.getName());
            vo.setAttributes(CollUtils.convertList(attribute, ProductAttributeConvert.INSTANCE::convertToVo));
            res.add(vo);
        }
        return res;
    }
}
