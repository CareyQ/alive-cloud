package com.careyq.alive.module.infra.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 对象存储配置 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 对象存储配置 DTO")
public class OssConfigDTO {

    @Schema(description = "主键")
    private Long id;

    @NotBlank(message = "配置名称不能为空")
    @Schema(description = "配置名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = "accessKey不能为空")
    @Schema(description = "accessKey", requiredMode = Schema.RequiredMode.REQUIRED)
    private String accessKey;

    @NotBlank(message = "秘钥不能为空")
    @Schema(description = "秘钥", requiredMode = Schema.RequiredMode.REQUIRED)
    private String secretKey;

    @NotBlank(message = "桶名称不能为空")
    @Schema(description = "桶名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String bucket;

    @NotBlank(message = "访问站点不能为空")
    @Schema(description = "访问站点", requiredMode = Schema.RequiredMode.REQUIRED)
    private String endpoint;

    @Schema(description = "域", requiredMode = Schema.RequiredMode.REQUIRED)
    private String region;

    @NotNull(message = "是否为主配置不能为空")
    @Schema(description = "是否为主配置", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean master;

}