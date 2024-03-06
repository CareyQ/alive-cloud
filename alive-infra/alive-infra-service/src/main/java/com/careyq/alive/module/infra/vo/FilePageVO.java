package com.careyq.alive.module.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 文件分页 VO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 文件分页 VO")
public class FilePageVO {

    @Schema(description = "编号")
    private Long id;

    @Schema(description = "配置编号")
    private Long configId;

    @Schema(description = "配置名称")
    private String configName;

    @Schema(description = "文件名")
    private String name;

    @Schema(description = "文件路径")
    private String path;

    @Schema(description = "文件 URL")
    private String url;

    @Schema(description = "文件类型")
    private String type;

    @Schema(description = "文件大小")
    private Integer size;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}