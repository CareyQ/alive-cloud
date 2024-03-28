package com.careyq.alive.search.api;

import com.careyq.alive.search.dto.EsProductDTO;
import com.careyq.alive.search.service.EsProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 商品 API 实现
 *
 * @author CareyQ
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class EsProductApiImpl implements EsProductApi {

    private final EsProductService esProductService;

    @Override
    public boolean upProduct(EsProductDTO dto) {
        try {
            esProductService.upProduct(dto);
        } catch (IOException e) {
            log.error("上架商品失败", e);
            return false;
        }
        return true;
    }
}
