package com.careyq.alive.module.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 代码生成表详情
 *
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
@Schema(description = "管理后台 - 代码生成表详情 VO")
public class CodegenDetailVO {

    @Schema(description = "表信息")
    private CodegenTableVO table;

    @Schema(description = "表字段")
    private List<CodegenColumnVO> columns;

}
