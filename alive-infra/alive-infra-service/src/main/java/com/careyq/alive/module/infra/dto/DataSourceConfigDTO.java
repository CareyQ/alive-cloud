package com.careyq.alive.module.infra.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 数据源配置 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 数据源配置 DTO")
public class DataSourceConfigDTO {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "操作人员")
    private String name;

    @Schema(description = "数据源地址")
    private String url;

    @Schema(description = "用户名")
    private String username;

    @JsonIgnore
    @Schema(description = "密码")
    private String password;
}
