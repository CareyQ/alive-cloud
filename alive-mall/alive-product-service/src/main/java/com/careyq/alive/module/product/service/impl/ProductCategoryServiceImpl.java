package com.careyq.alive.module.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.core.enums.CommonStatusEnum;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.module.product.convert.ProductConvert;
import com.careyq.alive.module.product.dto.ProductCategoryDTO;
import com.careyq.alive.module.product.entity.ProductCategory;
import com.careyq.alive.module.product.mapper.ProductCategoryMapper;
import com.careyq.alive.module.product.service.ProductCategoryService;
import com.careyq.alive.module.product.vo.ProductCategoryVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.careyq.alive.module.product.constants.ProductResultCode.*;

/**
 * 商品分类 服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveCategory(ProductCategoryDTO dto) {
        boolean isRoot = dto.getParentId().equals(ProductCategory.PARENT_ID_NULL);
        if (!isRoot) {
            this.checkDataExists(dto.getParentId());
        }
        boolean exists = this.lambdaQueryX()
                .neIfPresent(ProductCategory::getId, dto.getId())
                .eq(ProductCategory::getName, dto.getName())
                .exists();
        if (exists) {
            throw new CustomException(CATEGORY_NAME_IS_EXISTS);
        }

        if (dto.getId() != null) {
            ProductCategory productCategory = this.checkDataExists(dto.getId());
            if (productCategory.getParentId().equals(ProductCategory.PARENT_ID_NULL) && !isRoot) {
                throw new CustomException(CATEGORY_PARENT_NOT_ROOT);
            }
        }

        ProductCategory category = BeanUtil.copyProperties(dto, ProductCategory.class);
        this.saveOrUpdate(category);
        return category.getId();
    }

    @Override
    public List<ProductCategoryVO> getCategoryList(String name, Long parentId, Integer status) {
        List<ProductCategory> list = this.lambdaQueryX()
                .eqIfPresent(ProductCategory::getParentId, parentId)
                .eqIfPresent(ProductCategory::getStatus, status)
                .likeIfPresent(ProductCategory::getName, name)
                .orderByAsc(ProductCategory::getSort)
                .list();
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return CollUtils.convertList(list, ProductConvert.INSTANCE::categoryConvert);
    }

    @Override
    public ProductCategoryVO getCategoryDetail(Long id) {
        ProductCategory data = this.checkDataExists(id);
        return BeanUtil.copyProperties(data, ProductCategoryVO.class);
    }

    @Override
    public void delCategory(Long id) {
        this.checkDataExists(id);
        if (this.lambdaQueryX().eq(ProductCategory::getParentId, id).exists()) {
            throw new CustomException(CATEGORY_HAS_CHILDREN);
        }
        this.removeById(id);
    }

    /**
     * 校验商品分类是否存在
     *
     * @param id 编号
     * @return 商品分类
     */
    private ProductCategory checkDataExists(Long id) {
        if (id == null) {
            return null;
        }
        ProductCategory data = this.getById(id);
        if (data == null) {
            throw new CustomException(CATEGORY_NOT_EXISTS);
        }
        return data;
    }

    @Override
    public List<Tree<Long>> getCategoryTree() {
        List<ProductCategory> list = this.lambdaQueryX()
                .eq(ProductCategory::getStatus, CommonStatusEnum.ENABLE.getStatus())
                .list();
        return TreeUtil.build(list, ProductCategory.PARENT_ID_NULL, (node, tree) -> {
            tree.setId(node.getId())
                    .setParentId(node.getParentId())
                    .setName(node.getName());
        });
    }

    @Override
    public void validateCategory(Long id) {
        ProductCategory category = this.getById(id);
        if (category == null) {
            throw new CustomException(CATEGORY_NOT_EXISTS);
        }
        if (Objects.equals(category.getStatus(), CommonStatusEnum.DISABLE.getStatus())) {
            throw new CustomException(CATEGORY_DISABLED, category.getName());
        }
    }

    @Override
    public Map<Long, String> getCategoryNameMap(List<Long> ids) {
        if (CollUtils.isEmpty(ids)) {
            return MapUtil.newHashMap();
        }
        ids = CollUtils.distinct(ids);
        return CollUtils.convertMap(this.listByIds(ids), ProductCategory::getId, ProductCategory::getName);
    }
}
