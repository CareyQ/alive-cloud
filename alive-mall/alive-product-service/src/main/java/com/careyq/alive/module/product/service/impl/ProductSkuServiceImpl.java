package com.careyq.alive.module.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.module.product.dto.ProductSkuDTO;
import com.careyq.alive.module.product.entity.ProductSku;
import com.careyq.alive.module.product.mapper.ProductSkuMapper;
import com.careyq.alive.module.product.service.ProductSkuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.careyq.alive.module.product.constants.ProductResultCode.*;

/**
 * 商品 SKU 信息 服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSku> implements ProductSkuService {

    @Override
    public void validateSkus(List<ProductSkuDTO> skus) {
        if (CollUtils.isEmpty(skus)) {
            throw new CustomException(SKU_NOT_EXISTS);
        }
        // 校验 SKU 规格是否重复/规格数量是否一致
        int size = skus.getFirst().getSpec().size();
        for (ProductSkuDTO sku : skus) {
            Set<String> specName = new HashSet<>();
            if (sku.getSpec().size() != size) {
                throw new CustomException(SKU_SPEC_NOT_EQUALS);
            }
            for (ProductSku.Spec spec : sku.getSpec()) {
                if (!specName.add(spec.getAttributeName())) {
                    throw new CustomException(SKU_SPEC_DUPLICATED);
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createProductSku(Long productId, List<ProductSkuDTO> skus) {
        List<ProductSku> skuList = skus.stream().map(sku -> {
            ProductSku productSku = new ProductSku();
            productSku.setProductId(productId)
                    .setSpec(sku.getSpec())
                    .setSkuCode(sku.getSkuCode())
                    .setPrice(sku.getPrice())
                    .setMarketPrice(sku.getMarketPrice())
                    .setStock(sku.getStock())
                    .setAlbumPics(sku.getAlbumPics())
                    .setWeight(sku.getWeight())
                    .setVolume(sku.getVolume());
            return productSku;
        }).toList();
        this.saveBatch(skuList);
    }
}
