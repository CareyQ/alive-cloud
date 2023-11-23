package com.careyq.alive.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * 角色 VO
 *
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Schema(description = "管理后台 - 角色 VO")
public class RoleVO {

    @Schema(description = "主键")
    private Long id;

    @Length(max = 20, message = "角色名称长度不能超过{max}位")
    @NotBlank(message = "角色名称不能为空")
    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "是否是默认")
    private Boolean isDefault;

}
