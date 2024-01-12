package com.careyq.alive.module.system.dto;

import com.careyq.alive.core.domain.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * 用户信息F DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 用户信息 DTO")
public class UserPageDTO extends PageDTO {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "部门")
    private Long deptId;

    @Schema(description = "性别")
    private Integer gender;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "创建开始日期")
    private LocalDate createStartDate;

    @Schema(description = "创建结束日期")
    private LocalDate createEndDate;
}
