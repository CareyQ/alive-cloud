package com.careyq.alive.module.infra.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 数据库表 VO
 *
 * @author CareyQ
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "管理后台 - 数据库的表定义 VO")
public class DbTableVO {

    @Schema(description = "表名称")
    private String name;

    @Schema(description = "表描述")
    private String comment;

}
