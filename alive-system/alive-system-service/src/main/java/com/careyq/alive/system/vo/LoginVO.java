package com.careyq.alive.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 登录返回 VO
 *
 * @author CareyQ
 */
@Data
@Builder
@Schema(description = "管理后台 - 登录响应 VO")
public class LoginVO {

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;

    @Schema(description = "访问令牌", requiredMode = Schema.RequiredMode.REQUIRED)
    private String accessToken;

    @Schema(description = "刷新令牌", requiredMode = Schema.RequiredMode.REQUIRED)
    private String refreshToken;

    @Schema(description = "过期时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime expiresTime;
}
