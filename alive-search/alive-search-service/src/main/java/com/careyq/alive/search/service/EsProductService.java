package com.careyq.alive.search.service;

import com.careyq.alive.search.dto.EsProductDTO;

import java.io.IOException;

/**
 * ES 商品服务
 *
 * @author CareyQ
 */
public interface EsProductService {

    /**
     * 上架商品
     *
     * @param dto 商品信息
     * @throws IOException IO异常
     */
    void upProduct(EsProductDTO dto) throws IOException;
}
