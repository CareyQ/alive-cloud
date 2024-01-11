package com.careyq.alive.module.infra.dto;

import com.careyq.alive.core.domain.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * 代码生成表定义分页 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 代码生成表定义分页 DTO")
public class CodegenTablePageDTO extends PageDTO {

    @Schema(description = "表名称")
    private String tableName;

    @Schema(description = "表描述")
    private String tableComment;

    @Schema(description = "开始日期")
    private LocalDate startDate;

    @Schema(description = "结束日期")
    private LocalDate endDate;
}
