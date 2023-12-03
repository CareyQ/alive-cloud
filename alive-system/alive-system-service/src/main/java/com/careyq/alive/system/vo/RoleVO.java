package com.careyq.alive.system.vo;

import com.careyq.alive.system.dto.RoleDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 角色 VO
 *
 * @author CareyQ
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "管理后台 - 角色 VO")
public class RoleVO extends RoleDTO {

    @Schema(description = "是否是默认")
    private Boolean isDefault;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    public RoleVO(Long id, String name, String code, String remark, Boolean isDefault, LocalDateTime createTime) {
        super(id, name, code, remark);
        this.isDefault = isDefault;
        this.createTime = createTime;
    }
}
