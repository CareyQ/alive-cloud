package com.careyq.alive.module.system.dto;

import com.careyq.alive.core.domain.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 字典数据分页 DTO
 *
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "管理后台 - 字典数据分页 DTO")
public class DictDataPageDTO extends PageDTO {

    @Schema(description = "字典标签")
    private String label;

    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "状态")
    private Integer status;
}
