package com.careyq.alive.module.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 操作日志 VO
 *
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
@Schema(description = "管理后台 - 操作日志 VO")
public class OperateLogPageVO {

    /**
     * 日志编号
     */
    @Schema(description = "日志编号")
    private Long id;

    /**
     * 用户名称
     */
    @Schema(description = "用户名称")
    private String username;

    /**
     * 用户类型
     */
    @Schema(description = "日志类型")
    private Integer userType;

    /**
     * 操作模块
     */
    @Schema(description = "操作模块")
    private String module;

    /**
     * 操作名
     */
    @Schema(description = "操作名")
    private String name;

    /**
     * 操作分类
     */
    @Schema(description = "操作分类")
    private Integer type;

    /**
     * 开始时间
     */
    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    /**
     * 执行时长，单位：毫秒
     */
    @Schema(description = "执行时长")
    private Integer duration;

    /**
     * 结果码
     */
    @Schema(description = "结果码")
    private Integer resultCode;

}
