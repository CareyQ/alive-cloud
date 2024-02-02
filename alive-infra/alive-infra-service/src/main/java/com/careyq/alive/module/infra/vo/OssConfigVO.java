package com.careyq.alive.module.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 对象存储配置 VO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 对象存储配置 VO")
public class OssConfigVO {

    @Schema(description = "编号")
    private Long id;

    @Schema(description = "配置名称")
    private String name;

    @Schema(description = "accessKey")
    private String accessKey;

    @Schema(description = "秘钥")
    private String secretKey;

    @Schema(description = "桶名称")
    private String bucket;

    @Schema(description = "访问站点")
    private String endpoint;

    @Schema(description = "域")
    private String region;

    @Schema(description = "是否为主配置")
    private Boolean master;

}