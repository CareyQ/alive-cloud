package com.careyq.alive.module.infra.dto;

import com.careyq.alive.core.domain.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * 错误日志分页 DTO
 *
 * @author CareyQ
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "管理后台 - 错误日志分页 DTO")
public class ErrorLogPageDTO extends PageDTO {

    @Schema(description = "操作人员")
    private String nickname;

    @Schema(description = "用户类型")
    private Integer userType;

    @Schema(description = "请求地址")
    private String requestUrl;

    @Schema(description = "处理状态")
    private Integer processStatus;

    @Schema(description = "开始日期")
    private LocalDate startDate;

    @Schema(description = "结束日期")
    private LocalDate endDate;
}
