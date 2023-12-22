package com.careyq.alive.module.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 操作日志 VO
 *
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
@Schema(description = "管理后台 - 操作日志 VO")
public class OperateLogVO {

    @Schema(description = "日志编号")
    private Long id;

    @Schema(description = "链路追踪编号")
    private String traceId;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "用户类型")
    private Integer userType;

    @Schema(description = "操作模块")
    private String module;

    @Schema(description = "操作名")
    private String name;

    @Schema(description = "操作分类")
    private Integer type;

    @Schema(description = "操作明细")
    private String content;

    @Schema(description = "拓展字段")
    private Map<String, Object> extra;

    @Schema(description = "请求方法名")
    private String requestMethod;

    @Schema(description = "请求地址")
    private String requestUrl;

    @Schema(description = "IP")
    private String ip;

    @Schema(description = "设备信息")
    private String device;

    @Schema(description = "Java 方法名")
    private String javaMethod;

    @Schema(description = "Java 方法参数")
    private String javaMethodArgs;

    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "执行时长")
    private Integer duration;

    @Schema(description = "结果码")
    private Integer resultCode;

    @Schema(description = "结果码")
    private String resultMsg;

    @Schema(description = "结果数据")
    private String resultData;

    @Schema(description = "创建人")
    private Long creator;

}
