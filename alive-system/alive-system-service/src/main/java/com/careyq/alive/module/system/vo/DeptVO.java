package com.careyq.alive.module.system.vo;

import com.careyq.alive.module.system.dto.DeptDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 部门 VO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 部门 VO")
public class DeptVO extends DeptDTO {

    @Schema(description = "负责人名称")
    private String managerName;
}
