package com.careyq.alive.module.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * 字典数据 VO
 *
 * @author CareyQ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Schema(description = "管理后台 - 字典数据 VO")
public class DictDataVO {

    @Schema(description = "主键")
    private Long id;

    @Length(max = 50, message = "字典标签长度不能超过{max}位")
    @NotBlank(message = "字典标签不能为空")
    @Schema(description = "字典标签")
    private String label;

    @Length(max = 50, message = "字典标签长度不能超过{max}位")
    @NotBlank(message = "字典键值不能为空")
    @Schema(description = "字典键值")
    private String value;

    @Length(max = 50, message = "字典类型长度不能超过{max}位")
    @NotBlank(message = "字典类型不能为空")
    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "颜色类型")
    private String colorType;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "状态")
    private Integer status;
}
