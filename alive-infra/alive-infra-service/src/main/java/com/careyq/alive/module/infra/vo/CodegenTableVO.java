package com.careyq.alive.module.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 代码生成表定义 VO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 代码生成表定义 VO")
public class CodegenTableVO {

    @Schema(description = "编号")
    private Long id;

    @Schema(description = "数据源配置编号")
    private Long dataSourceConfigId;

    @NotBlank(message = "表名称不能为空")
    @Schema(description = "表名称")
    private String tableName;

    @Schema(description = "表描述")
    private String tableComment;

    @NotNull(message = "生成场景不能为空")
    @Schema(description = "生成场景")
    private Integer scene;

    @NotBlank(message = "模块名称不能为空")
    @Schema(description = "模块名称")
    private String moduleName;

    @NotBlank(message = "业务名称不能为空")
    @Schema(description = "业务名称")
    private String businessName;

    @NotBlank(message = "类名称不能为空")
    @Schema(description = "类名称")
    private String className;

    @Schema(description = "类描述")
    private String classComment;

    @NotBlank(message = "作者不能为空")
    @Schema(description = "作者")
    private String author;

}
