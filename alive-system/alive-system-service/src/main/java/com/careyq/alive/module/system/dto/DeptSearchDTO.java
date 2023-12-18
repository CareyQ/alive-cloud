package com.careyq.alive.module.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 部门筛选 DTO
 *
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
@Schema(description = "管理后台 - 部门筛选 DTO")
public class DeptSearchDTO {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "状态")
    private Integer status;
}
