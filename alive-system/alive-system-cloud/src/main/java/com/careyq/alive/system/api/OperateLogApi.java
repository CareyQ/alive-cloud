package com.careyq.alive.system.api;

import com.careyq.alive.core.domain.Result;
import com.careyq.alive.system.dto.OperateLogDTO;
import com.careyq.alive.system.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 操作日志 RPC
 *
 * @author CareyQ
 */
@FeignClient(name = ApiConstants.NAME, path = ApiConstants.PREFIX + "/operate-log")
@Tag(name = "RPC - 操作日志")
public interface OperateLogApi {

    /**
     * 创建操作日志
     *
     * @param dto 日志信息
     * @return 结果
     */
    @PostMapping("/create")
    @Operation(summary = "创建操作日志")
    Result<Boolean> createOperateLog(@Validated @RequestBody OperateLogDTO dto);
}
