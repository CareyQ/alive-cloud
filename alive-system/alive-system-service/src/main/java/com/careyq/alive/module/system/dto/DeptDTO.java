package com.careyq.alive.module.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * 部门 DTO
 *
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 部门 DTO")
public class DeptDTO {

    @Schema(description = "主键")
    private Long id;

    @Length(max = 20, message = "部门名称长度不能超过{max}位")
    @NotBlank(message = "部门名称不能为空")
    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "父级部门")
    private Long parentId;

    @Schema(description = "显示排序")
    private Integer sort;

    @Schema(description = "负责人 ID")
    private Long managerId;

    @Schema(description = "联系电话")
    private String mobile;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "状态")
    private Integer status;
}
