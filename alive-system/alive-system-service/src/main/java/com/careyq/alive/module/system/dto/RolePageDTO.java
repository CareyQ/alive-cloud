package com.careyq.alive.module.system.dto;

import com.careyq.alive.core.domain.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 角色分页 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 角色分页 DTO")
public class RolePageDTO extends PageDTO {

    @Schema(description = "名称")
    private String name;

}
