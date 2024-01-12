package com.careyq.alive.module.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 部门筛选 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 部门筛选 DTO")
public class DeptSearchDTO {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "状态")
    private Integer status;
}
