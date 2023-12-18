package com.careyq.alive.module.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * 角色 DTO
 *
 * @author CareyQ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "管理后台 - 角色 DTO")
public class RoleDTO {

    @Schema(description = "主键")
    private Long id;

    @Length(max = 20, message = "角色名称长度不能超过{max}位")
    @NotBlank(message = "角色名称不能为空")
    @Schema(description = "角色名称")
    private String name;

    @Length(max = 50, message = "角色标识长度不能超过{max}位")
    @NotBlank(message = "角色标识不能为空")
    @Schema(description = "角色标识")
    private String code;

    @Schema(description = "备注")
    private String remark;

}
