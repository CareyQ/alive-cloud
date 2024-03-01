package com.careyq.alive.module.system.api;

import com.careyq.alive.module.system.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 用户 API 接口
 *
 * @author CareyQ
 */
@FeignClient(name = ApiConstants.NAME)
@Tag(name = "RPC 服务 - 用户")
public interface UserApi {

    String PREFIX = ApiConstants.PREFIX + "/user";

    /**
     * 获取用户名称
     *
     * @param userId 用户编号
     * @return 用户名称
     */
    @GetMapping(PREFIX + "/nickname")
    @Operation(summary = "获取用户昵称")
    String getNickname(Long userId);
}
