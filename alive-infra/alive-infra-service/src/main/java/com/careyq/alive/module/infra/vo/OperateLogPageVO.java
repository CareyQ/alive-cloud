package com.careyq.alive.module.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 操作日志分页 VO
 *
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
@Schema(description = "管理后台 - 操作日志分页 VO")
public class OperateLogPageVO {

    @Schema(description = "日志编号")
    private Long id;

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

    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "执行时长")
    private Integer duration;

    @Schema(description = "结果码")
    private Integer resultCode;

}
