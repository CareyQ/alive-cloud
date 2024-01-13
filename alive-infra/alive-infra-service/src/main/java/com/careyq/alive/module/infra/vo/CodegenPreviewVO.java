package com.careyq.alive.module.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 预览代码生成 VO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 预览代码生成 VO")
public class CodegenPreviewVO {

    @Schema(description = "文件路径")
    private String filePath;

    @Schema(description = "代码")
    private String code;

}
