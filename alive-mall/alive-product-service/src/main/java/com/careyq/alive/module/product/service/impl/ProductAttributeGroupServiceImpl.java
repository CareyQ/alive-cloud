package com.careyq.alive.module.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.core.domain.EntryVO;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.module.product.convert.ProductAttributeConvert;
import com.careyq.alive.module.product.dto.ProductAttributeGroupDTO;
import com.careyq.alive.module.product.dto.ProductAttributeGroupPageDTO;
import com.careyq.alive.module.product.entity.ProductAttributeGroup;
import com.careyq.alive.module.product.entity.ProductCategory;
import com.careyq.alive.module.product.mapper.ProductAttributeGroupMapper;
import com.careyq.alive.module.product.service.ProductAttributeGroupService;
import com.careyq.alive.module.product.service.ProductCategoryService;
import com.careyq.alive.module.product.vo.ProductAttributeGroupPageVO;
import com.careyq.alive.module.product.vo.ProductAttributeGroupVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.careyq.alive.module.product.constants.ProductResultCode.ATTRIBUTE_GROUP_NOT_EXISTS;

/**
 * 商品属性分组 服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class ProductAttributeGroupServiceImpl extends ServiceImpl<ProductAttributeGroupMapper, ProductAttributeGroup> implements ProductAttributeGroupService {

    private final ProductCategoryService categoryService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveAttributeGroup(ProductAttributeGroupDTO dto) {
        boolean exists = this.lambdaQueryX()
                .neIfPresent(ProductAttributeGroup::getId, dto.getId())
                .eq(ProductAttributeGroup::getName, dto.getName())
                .exists();
        if (exists) {
            throw new CustomException();
        }
        if (dto.getId() != null) {
            this.checkDataExists(dto.getId());
        }
        ProductAttributeGroup attributeGroup = BeanUtil.copyProperties(dto, ProductAttributeGroup.class);
        this.saveOrUpdate(attributeGroup);
        return attributeGroup.getId();
    }

    @Override
    public IPage<ProductAttributeGroupPageVO> getAttributeGroupPage(ProductAttributeGroupPageDTO dto) {
        IPage<ProductAttributeGroup> page = this.lambdaQueryX()
                .eqIfPresent(ProductAttributeGroup::getCategoryId, dto.getCategoryId())
                .likeIfPresent(ProductAttributeGroup::getName, dto.getName())
                .dateBetween(ProductAttributeGroup::getCreateTime, dto.getStartDate(), dto.getEndDate())
                .orderByAsc(ProductAttributeGroup::getSort)
                .page(new Page<>(dto.getCurrent(), dto.getSize()));
        if (page.getRecords().isEmpty()) {
            return new Page<>();
        }
        List<Long> categoryIds = CollUtils.convertList(page.getRecords(), ProductAttributeGroup::getCategoryId);
        Map<Long, String> categoryMap = CollUtils.convertMap(categoryService.listByIds(categoryIds), ProductCategory::getId, ProductCategory::getName);
        return page.convert(e -> ProductAttributeConvert.INSTANCE.groupConvert(e, categoryMap));
    }

    @Override
    public ProductAttributeGroupVO getAttributeGroupDetail(Long id) {
        ProductAttributeGroup data = this.checkDataExists(id);
        return BeanUtil.copyProperties(data, ProductAttributeGroupVO.class);
    }

    @Override
    public List<EntryVO> getAttributeGroupList(Long categoryId) {
        List<ProductAttributeGroup> list = this.lambdaQuery()
                .eq(ProductAttributeGroup::getCategoryId, categoryId)
                .list();
        list.sort(Comparator.comparing(ProductAttributeGroup::getSort));
        return CollUtils.convertList(list, e -> new EntryVO(e.getId(), e.getName()));
    }

    @Override
    public void delAttributeGroup(Long id) {
        this.checkDataExists(id);
        this.removeById(id);
    }

    /**
     * 校验商品属性分组是否存在
     *
     * @param id 编号
     * @return 商品属性分组
     */
    private ProductAttributeGroup checkDataExists(Long id) {
        if (id == null) {
            return null;
        }
        ProductAttributeGroup data = this.getById(id);
        if (data == null) {
            throw new CustomException(ATTRIBUTE_GROUP_NOT_EXISTS);
        }
        return data;
    }

}
