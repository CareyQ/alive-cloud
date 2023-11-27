package com.careyq.alive.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * 字典类型 VO
 *
 * @author CareyQ
 */
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "管理后台 - 字典类型 VO")
public class DictTypeVO {

    @Schema(description = "主键")
    private Long id;

    @Length(max = 20, message = "字典名称长度不能超过{max}位")
    @NotBlank(message = "字典名称不能为空")
    @Schema(description = "字典名称")
    private String name;

    @Length(max = 50, message = "字典类型长度不能超过{max}位")
    @NotBlank(message = "字典类型不能为空")
    @Schema(description = "字典类型")
    private String type;

    @Schema(description = "字典类型状态")
    private Integer status;
}
