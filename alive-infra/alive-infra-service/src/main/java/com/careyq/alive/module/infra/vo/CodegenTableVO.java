package com.careyq.alive.module.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 代码生成表定义 VO
 *
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
@Schema(description = "管理后台 - 代码生成表定义 VO")
public class CodegenTableVO {

    @Schema(description = "编号")
    private Long id;

    @Schema(description = "数据源配置编号")
    private Long dataSourceConfigId;

    @Schema(description = "表名称")
    private String tableName;

    @Schema(description = "表描述")
    private String tableComment;

    @Schema(description = "模块名称")
    private String moduleName;

    @Schema(description = "业务名称")
    private String businessName;

    @Schema(description = "类名称")
    private String className;

    @Schema(description = "类描述")
    private String classComment;

    @Schema(description = "作者")
    private String author;

}
