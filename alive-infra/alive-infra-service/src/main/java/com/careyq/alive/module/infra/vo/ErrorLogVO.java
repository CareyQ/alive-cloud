package com.careyq.alive.module.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 错误日志 VO
 *
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
@Schema(description = "管理后台 - 错误日志 VO")
public class ErrorLogVO {

    @Schema(description = "日志编号")
    private Long id;

    @Schema(description = "链路追踪编号")
    private String traceId;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "用户类型")
    private Integer userType;

    @Schema(description = "请求方法名")
    private String requestMethod;

    @Schema(description = "请求地址")
    private String requestUrl;

    @Schema(description = "请求参数")
    private String requestParams;

    @Schema(description = "请求 curl")
    private String requestCurl;

    @Schema(description = "IP")
    private String ip;

    @Schema(description = "异常类全名")
    private String exName;

    @Schema(description = "异常消息")
    private String exMessage;

    @Schema(description = "异常导致的根消息")
    private String exRootCauseMessage;

    @Schema(description = "异常的栈轨迹")
    private String exStackTrace;

    @Schema(description = "处理状态")
    private Integer processStatus;

    @Schema(description = "处理时间")
    private LocalDateTime processTime;

    @Schema(description = "处理人的用户 ID")
    private Long processUserId;

    @Schema(description = "处理人的用户名称")
    private String processUsername;

}
