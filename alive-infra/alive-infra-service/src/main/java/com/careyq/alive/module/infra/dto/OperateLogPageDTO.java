package com.careyq.alive.module.infra.dto;

import com.careyq.alive.core.domain.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * 操作日志分页 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 操作日志分页 DTO")
public class OperateLogPageDTO extends PageDTO {

    @Schema(description = "操作人员")
    private String nickname;

    @Schema(description = "操作模块")
    private String module;

    @Schema(description = "操作类型")
    private Integer type;

    @Schema(description = "是否成功")
    private Boolean success;

    @Schema(description = "开始日期")
    private LocalDate startDate;

    @Schema(description = "结束日期")
    private LocalDate endDate;
}
