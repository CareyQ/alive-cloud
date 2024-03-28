package com.careyq.alive.search.api;

import com.careyq.alive.search.dto.EsProductDTO;
import com.careyq.alive.search.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 商品 API 接口
 *
 * @author CareyQ
 */
@Primary
@FeignClient(name = ApiConstants.NAME)
@Tag(name = "RPC 服务 - 商品")
public interface EsProductApi {

    String PREFIX = ApiConstants.PREFIX + "/product";

    /**
     * 上架商品
     *
     * @param dto 商品信息
     * @return 是否成功
     */
    @PostMapping(PREFIX + "/up")
    @Operation(summary = "上架商品")
    boolean upProduct(@Validated @RequestBody EsProductDTO dto);

}
