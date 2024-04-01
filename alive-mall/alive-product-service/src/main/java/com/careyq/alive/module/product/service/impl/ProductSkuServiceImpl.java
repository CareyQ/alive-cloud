package com.careyq.alive.module.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.module.product.convert.ProductConvert;
import com.careyq.alive.module.product.dto.ProductSkuDTO;
import com.careyq.alive.module.product.entity.Product;
import com.careyq.alive.module.product.entity.ProductSku;
import com.careyq.alive.module.product.mapper.ProductSkuMapper;
import com.careyq.alive.module.product.service.ProductAttributeService;
import com.careyq.alive.module.product.service.ProductSkuService;
import com.careyq.alive.module.product.util.SnUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.careyq.alive.module.product.constants.ProductResultCode.*;

/**
 * 商品 SKU 信息 服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSku> implements ProductSkuService {

    private final ProductAttributeService attributeService;

    @Override
    public void validateSkus(List<ProductSkuDTO> skus) {
        if (CollUtils.isEmpty(skus)) {
            throw new CustomException(SKU_NOT_EXISTS);
        }
        // 校验 SKU 规格是否重复/规格数量是否一致
        int size = skus.getFirst().getSpec().size();
        for (ProductSkuDTO sku : skus) {
            Set<String> specName = new LinkedHashSet<>();
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
    public void createProductSku(Product product, List<ProductSkuDTO> skus) {
        AtomicInteger index = new AtomicInteger();
        List<ProductSku> skuList = skus.stream().map(e -> {
            e.setProductId(product.getId());
            ProductSku productSku = ProductConvert.INSTANCE.skuConvert(e);
            setSnCode(product, index.getAndIncrement(), productSku);
            return productSku;
        }).toList();
        this.saveBatch(skuList);

        attributeService.updateAttributeValue(product.getId(), CollUtils.convertList(skus, ProductSkuDTO::getSpec));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProductSku(Product product, List<ProductSkuDTO> skus) {
        List<ProductSku> existsSkus = this.lambdaQuery()
                .eq(ProductSku::getProductId, product.getId())
                .list();
        Set<Long> existsSkuIds = new HashSet<>(CollUtils.convertList(existsSkus, ProductSku::getId));
        List<ProductSku> updateSkus = skus.stream().map(e -> ProductConvert.INSTANCE.skuConvert(e, product.getId())).toList();

        List<ProductSku> update = new ArrayList<>();
        List<ProductSku> create = new ArrayList<>();
        int index = 0;
        for (ProductSku sku : updateSkus) {
            if (sku.getId() != null && existsSkuIds.contains(sku.getId())) {
                update.add(sku);
                existsSkuIds.remove(sku.getId());
                continue;
            }
            setSnCode(product, index, sku);
            create.add(sku);
            index++;
        }

        if (!update.isEmpty()) {
            this.updateBatchById(update);
        }
        if (!create.isEmpty()) {
            this.saveBatch(create);
        }
        if (!existsSkuIds.isEmpty()) {
            this.removeBatchByIds(existsSkuIds);
        }
        attributeService.updateAttributeValue(product.getId(), CollUtils.convertList(skus, ProductSkuDTO::getSpec));
    }

    private static void setSnCode(Product product, int index, ProductSku sku) {
        String snCode = SnUtils.generateSnCode(product.getBrandId(), product.getCategoryId(), product.getId(), index);
        sku.setSnCode(snCode);
    }
}
