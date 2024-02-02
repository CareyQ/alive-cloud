package com.careyq.alive.module.infra.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 文件 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 文件 DTO")
public class FileDTO {

    @Schema(description = "主键")
    private Long id;

    @NotNull(message = "配置编号不能为空")
    @Schema(description = "配置编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long configId;

    @NotBlank(message = "文件名不能为空")
    @Schema(description = "文件名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = "文件路径不能为空")
    @Schema(description = "文件路径", requiredMode = Schema.RequiredMode.REQUIRED)
    private String path;

    @NotBlank(message = "文件 URL不能为空")
    @Schema(description = "文件 URL", requiredMode = Schema.RequiredMode.REQUIRED)
    private String url;

    @NotBlank(message = "文件类型不能为空")
    @Schema(description = "文件类型", requiredMode = Schema.RequiredMode.REQUIRED)
    private String type;

    @NotNull(message = "文件大小不能为空")
    @Schema(description = "文件大小", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer size;

}