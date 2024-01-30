package com.careyq.alive.module.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 代码生成表详情
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 代码生成表详情 VO")
public class CodegenDetailVO {

    @Valid
    @NotNull(message = "表定义不能为空")
    @Schema(description = "表信息")
    private CodegenTableVO table;

    @Valid
    @NotEmpty(message = "表字段不能为空")
    @Schema(description = "表字段")
    private List<CodegenColumnVO> columns;

}
