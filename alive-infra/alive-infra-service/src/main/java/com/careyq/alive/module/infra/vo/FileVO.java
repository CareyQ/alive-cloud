package com.careyq.alive.module.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文件 VO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 文件 VO")
public class FileVO {

    @Schema(description = "编号")
    private Long id;

    @Schema(description = "文件名")
    private String name;

    @Schema(description = "文件目录")
    private String folder;

    @Schema(description = "文件路径")
    private String path;

    @Schema(description = "文件 URL")
    private String url;

    @Schema(description = "文件类型")
    private String type;

    @Schema(description = "文件大小")
    private Integer size;

}