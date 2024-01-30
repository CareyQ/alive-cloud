package com.careyq.alive.module.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 代码生成表字段 VO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 代码生成表字段 VO")
public class CodegenColumnVO {

    @Schema(description = "编号")
    private Long id;

    @Schema(description = "表编号")
    private Long tableId;

    @NotBlank(message = "字段名称不能为空")
    @Schema(description = "字段名称")
    private String columnName;

    @NotBlank(message = "字段类型不能为空")
    @Schema(description = "字段类型")
    private String dataType;

    @NotBlank(message = "字段描述不能为空")
    @Schema(description = "字段描述")
    private String columnComment;

    @NotNull(message = "是否可以为空不能为空")
    @Schema(description = "是否可以为空")
    private Boolean nullable;

    @NotNull(message = "是否主键不能为空")
    @Schema(description = "是否主键")
    private Boolean primaryKey;

    @NotNull(message = "是否自增不能为空")
    @Schema(description = "是否自增")
    private Boolean autoIncrement;

    @NotBlank(message = "Java 属性类型不能为空")
    @Schema(description = "Java 属性类型")
    private String javaType;

    @NotBlank(message = "Java 属性名称不能为空")
    @Schema(description = "Java 属性名称")
    private String javaField;

    @Schema(description = "字典类型")
    private String dictType;

    @NotNull(message = "是否是创建操作字段不能为空")
    @Schema(description = "是否是创建操作字段")
    private Boolean createOperation;

    @NotNull(message = "是否是更新操作字段不能为空")
    @Schema(description = "是否是更新操作字段")
    private Boolean updateOperation;

    @NotNull(message = "是否是查询结果字段不能为空")
    @Schema(description = "是否是查询结果字段")
    private Boolean queryResult;

    @NotNull(message = "是否是查询条件字段不能为空")
    @Schema(description = "是否是查询条件字段")
    private Boolean queryCondition;

    @NotBlank(message = "查询时作为条件的类型不能为空")
    @Schema(description = "查询时作为条件的类型")
    private String queryType;

    @NotBlank(message = "前端显示类型不能为空")
    @Schema(description = "前端显示类型")
    private String htmlType;

}
