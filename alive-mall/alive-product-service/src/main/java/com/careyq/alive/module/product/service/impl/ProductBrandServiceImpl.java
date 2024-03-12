package com.careyq.alive.module.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.core.domain.EntryVO;
import com.careyq.alive.core.enums.CommonStatusEnum;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.module.product.convert.ProductConvert;
import com.careyq.alive.module.product.dto.ProductBrandDTO;
import com.careyq.alive.module.product.dto.ProductBrandPageDTO;
import com.careyq.alive.module.product.entity.ProductBrand;
import com.careyq.alive.module.product.mapper.ProductBrandMapper;
import com.careyq.alive.module.product.service.ProductBrandService;
import com.careyq.alive.module.product.vo.ProductBrandPageVO;
import com.careyq.alive.module.product.vo.ProductBrandVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.careyq.alive.module.product.constants.ProductResultCode.BRAND_NAME_IS_EXISTS;
import static com.careyq.alive.module.product.constants.ProductResultCode.BRAND_NOT_EXISTS;

/**
 * 商品品牌 服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class ProductBrandServiceImpl extends ServiceImpl<ProductBrandMapper, ProductBrand> implements ProductBrandService {

    private final ProductBrandMapper brandMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveBrand(ProductBrandDTO dto) {
        boolean exists = this.lambdaQueryX()
                .neIfPresent(ProductBrand::getId, dto.getId())
                .eq(ProductBrand::getName, dto.getName())
                .exists();
        if (exists) {
            throw new CustomException(BRAND_NAME_IS_EXISTS);
        }
        if (dto.getId() != null) {
            this.checkDataExists(dto.getId());
        }
        ProductBrand brand = BeanUtil.copyProperties(dto, ProductBrand.class);
        this.saveOrUpdate(brand);
        return brand.getId();
    }

    @Override
    public IPage<ProductBrandPageVO> getBrandPage(ProductBrandPageDTO dto) {
        IPage<ProductBrand> page = this.lambdaQueryX()
                .likeIfPresent(ProductBrand::getName, dto.getName())
                .eqIfPresent(ProductBrand::getStatus, dto.getStatus())
                .dateBetween(ProductBrand::getCreateTime, dto.getStartDate(), dto.getEndDate())
                .orderByDesc(ProductBrand::getId)
                .page(new Page<>(dto.getCurrent(), dto.getSize()));
        if (page.getRecords().isEmpty()) {
            return new Page<>();
        }
        return page.convert(ProductConvert.INSTANCE::brandConvert);
    }

    @Override
    public ProductBrandVO getBrandDetail(Long id) {
        ProductBrand data = this.checkDataExists(id);
        return BeanUtil.copyProperties(data, ProductBrandVO.class);
    }

    @Override
    public void delBrand(Long id) {
        this.checkDataExists(id);
        this.removeById(id);
    }

    /**
     * 校验商品品牌是否存在
     *
     * @param id 编号
     * @return 商品品牌
     */
    private ProductBrand checkDataExists(Long id) {
        if (id == null) {
            return null;
        }
        ProductBrand data = this.getById(id);
        if (data == null) {
            throw new CustomException(BRAND_NOT_EXISTS);
        }
        return data;
    }

    @Override
    public List<EntryVO> getBrandList() {
        List<ProductBrand> list = this.lambdaQuery()
                .eq(ProductBrand::getStatus, CommonStatusEnum.ENABLE.getStatus())
                .orderByAsc(ProductBrand::getSort)
                .list();
        return CollUtils.convertList(list, e -> new EntryVO(e.getId(), e.getName()));
    }
}
