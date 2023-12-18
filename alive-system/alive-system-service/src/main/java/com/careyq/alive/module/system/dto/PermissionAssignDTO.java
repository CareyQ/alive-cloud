package com.careyq.alive.module.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * 权限绑定 DTO
 *
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
@Schema(description = "管理后台 - 权限绑定 DTO")
public class PermissionAssignDTO {

    @NotNull(message = "主编号不能为空")
    @Schema(description = "主 ID")
    private Long primaryId;

    @Schema(description = "关联 ID")
    private Set<Long> linkIds;

}
