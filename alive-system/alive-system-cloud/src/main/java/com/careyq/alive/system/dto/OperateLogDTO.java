package com.careyq.alive.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 操作日志 DTO
 *
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
@Schema(description = "RPC - 操作日志 DTO")
public class OperateLogDTO {

    @Schema(description = "链路追踪编号")
    private String traceId;

    @NotNull(message = "用户编号不能为空")
    @Schema(description = "用户编号")
    private Long userId;

    @NotNull(message = "用户类型不能为空")
    @Schema(description = "用户类型")
    private Integer userType;

    @NotBlank(message = "操作模块不能为空")
    @Schema(description = "操作模块")
    private String module;

    @NotBlank(message = "操作名")
    @Schema(description = "操作名")
    private String name;

    @NotNull(message = "操作分类不能为空")
    @Schema(description = "操作分类")
    private Integer type;

    @Schema(description = "操作明细")
    private String content;

    @Schema(description = "拓展字段")
    private Map<String, Object> extra;

    @NotBlank(message = "请求方法名不能为空")
    @Schema(description = "请求方法名")
    private String requestMethod;

    @NotBlank(message = "请求地址不能为空")
    @Schema(description = "请求地址")
    private String requestUrl;

    @NotBlank(message = "IP 不能为空")
    @Schema(description = "IP")
    private String ip;

    @NotBlank(message = "设备信息不能为空")
    @Schema(description = "设备信息")
    private String device;

    @NotBlank(message = "Java 方法名不能为空")
    @Schema(description = "Java 方法名")
    private String javaMethod;

    @Schema(description = "Java 方法的参数")
    private String javaMethodArgs;

    @NotNull(message = "开始时间不能为空")
    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @NotNull(message = "执行时长不能为空")
    @Schema(description = "执行时长，单位：毫秒")
    private Integer duration;

    @NotNull(message = "结果码不能为空")
    @Schema(description = "结果码")
    private Integer resultCode;

    @Schema(description = "结果提示")
    private String resultMsg;

    @Schema(description = "结果数据")
    private String resultData;

    @Schema(description = "创建人")
    private Long creator;

    @Schema(description = "更新人")
    private Long updater;
}
