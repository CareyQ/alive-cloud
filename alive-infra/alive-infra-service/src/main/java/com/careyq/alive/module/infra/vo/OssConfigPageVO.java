package com.careyq.alive.module.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 对象存储配置分页 VO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 对象存储配置分页 VO")
public class OssConfigPageVO {

    @Schema(description = "编号")
    private Long id;

    @Schema(description = "配置名称")
    private String name;

    @Schema(description = "桶名称")
    private String bucket;

    @Schema(description = "访问站点")
    private String endpoint;

    @Schema(description = "域")
    private String region;

    @Schema(description = "是否为主配置")
    private Boolean master;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}