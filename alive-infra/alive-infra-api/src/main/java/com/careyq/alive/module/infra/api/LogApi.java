package com.careyq.alive.module.infra.api;

import com.careyq.alive.module.infra.dto.ErrorLogDTO;
import com.careyq.alive.module.infra.dto.LoginLogDTO;
import com.careyq.alive.module.infra.dto.OperateLogDTO;
import com.careyq.alive.module.infra.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 日志 API 接口
 *
 * @author CareyQ
 */
@Primary
@FeignClient(name = ApiConstants.NAME)
@Tag(name = "RPC 服务 - 日志")
public interface LogApi {

    String PREFIX = ApiConstants.PREFIX + "/log";

    /**
     * 创建登录日志
     *
     * @param dto 日志信息
     */
    @PostMapping( PREFIX + "/login-log")
    @Operation(summary = "创建登录日志")
    void createLoginLog(@Validated @RequestBody LoginLogDTO dto);

    /**
     * 创建操作日志
     *
     * @param dto 日志信息
     */
    @PostMapping(PREFIX + "/operate-log")
    @Operation(summary = "创建操作日志")
    void createOperateLog(@Validated @RequestBody OperateLogDTO dto);

    /**
     * 创建错误日志
     *
     * @param dto 日志信息
     */
    @PostMapping(PREFIX + "/error-log")
    @Operation(summary = "创建错误日志")
    void createErrorLog(@Validated @RequestBody ErrorLogDTO dto);
}
