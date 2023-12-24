package com.careyq.alive.module.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 错误日志分页 VO
 *
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
@Schema(description = "管理后台 - 错误日志分页 VO")
public class ErrorLogPageVO {

    @Schema(description = "日志编号")
    private Long id;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "用户类型")
    private Integer userType;

    @Schema(description = "请求地址")
    private String requestUrl;

    @Schema(description = "异常类全名")
    private String exName;

    @Schema(description = "处理状态")
    private Integer processStatus;

    @Schema(description = "错误时间")
    private LocalDateTime createTime;

}
