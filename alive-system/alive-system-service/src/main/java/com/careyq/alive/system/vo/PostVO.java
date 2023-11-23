package com.careyq.alive.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * 岗位 VO
 *
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Schema(description = "管理后台 - 岗位 VO")
public class PostVO {

    @Schema(description = "主键")
    private Long id;

    @Length(max = 20, message = "岗位名称长度不能超过{max}位")
    @NotBlank(message = "岗位名称不能为空")
    @Schema(description = "岗位名称")
    private String name;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "状态")
    private Integer status;
}
