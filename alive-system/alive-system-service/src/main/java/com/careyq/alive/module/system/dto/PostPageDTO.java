package com.careyq.alive.module.system.dto;

import com.careyq.alive.core.domain.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 岗位分页 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 岗位分页 DTO")
public class PostPageDTO extends PageDTO {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "状态")
    private Integer status;
}
