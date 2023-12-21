package com.careyq.alive.module.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 登录日志 VO
 *
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
@Schema(description = "管理后台 - 登录日志 VO")
public class LoginLogPageVO {

    @Schema(description = "日志类型")
    private Integer type;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "登录结果")
    private Integer result;

    @Schema(description = "IP")
    private String ip;

    @Schema(description = "IP 信息")
    private String ipInfo;

    @Schema(description = "设备")
    private String device;

    @Schema(description = "登录时间")
    private LocalDateTime loginTime;

}
