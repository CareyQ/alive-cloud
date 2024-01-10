package com.careyq.alive.module.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

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

    @Schema(description = "字段名称")
    private String columnName;

    @Schema(description = "字段类型")
    private String dataType;

    @Schema(description = "字段描述")
    private String columnComment;

    @Schema(description = "是否可以为空")
    private Boolean nullable;

    @Schema(description = "是否主键")
    private Boolean primaryKey;

    @Schema(description = "是否自增")
    private Boolean autoIncrement;

    @Schema(description = "Java 属性类型")
    private String javaType;

    @Schema(description = "Java 属性名称")
    private String javaField;

    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "是否是创建操作字段")
    private Boolean createOperation;

    @Schema(description = "是否是更新操作字段")
    private Boolean updateOperation;

    @Schema(description = "是否是查询结果字段")
    private Boolean queryResult;

    @Schema(description = "是否是查询条件字段")
    private Boolean queryCondition;

    @Schema(description = "查询时作为条件的类型")
    private String queryType;

    @Schema(description = "前端显示类型")
    private String htmlType;

}
