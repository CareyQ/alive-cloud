package com.careyq.alive.search.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import com.careyq.alive.search.constants.EsConstant;
import com.careyq.alive.search.dto.EsProductDTO;
import com.careyq.alive.search.service.EsProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * ES 商品服务实现
 *
 * @author CareyQ
 */
@Service
@RequiredArgsConstructor
public class EsProductServiceImpl implements EsProductService {

    private final ElasticsearchClient esClient;

    @Override
    public void upProduct(EsProductDTO dto) throws IOException {
        IndexRequest<EsProductDTO> request = IndexRequest.of(i -> i
                .index(EsConstant.PRODUCT_INDEX)
                .id(dto.getProductId().toString())
                .document(dto));
        esClient.index(request);
    }
}
