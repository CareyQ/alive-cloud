package com.careyq.alive.module.infra.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 导入数据库表结构 DTO
 *
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
@Schema(description = "管理后台 - 导入数据库表结构 DTO")
public class CodegenImportDTO {

    @NotNull(message = "数据源配置编号不能为空")
    @Schema(description = "数据源配置编号")
    private Long dataSourceConfigId;

    @NotEmpty(message = "表名列表不能为空")
    @Schema(description = "表名列表")
    private List<String> tableNames;

}
