package com.careyq.alive.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * @author CareyQ
 */
@Data
@Accessors(chain = true)
@Schema(description = "管理后台 - 菜单详情 VO")
public class MenuVO {

    @Schema(description = "菜单主键")
    private Long id;

    @Schema(description = "父菜单 ID")
    private Long parentId;

    @NotNull(message = "菜单类型不能为空")
    @Schema(description = "菜单类型")
    private Integer type;

    @Length(max = 20, message = "名称长度不能超过{max}位")
    @NotBlank(message = "菜单名称不能为空")
    @Schema(description = "菜单名称")
    private String name;

    @Length(max = 50, message = "权限标识长度不能超过{max}位")
    @Schema(description = "权限标识")
    private String permission;

    @Length(max = 100, message = "权限标识长度不能超过{max}位")
    @Schema(description = "路由地址，菜单类型为菜单或者目录时用")
    private String path;

    @Length(max = 100, message = "组件路径长度不能超过{max}位")
    @Schema(description = "组件路径，菜单类型为菜单时用")
    private String component;

    @Length(max = 100, message = "组件名长度不能超过{max}位")
    @Schema(description = "组件名")
    private String componentName;

    @Schema(description = "菜单图标，菜单类型为菜单或者目录时用")
    private String icon;

    @Schema(description = "是否缓存")
    private Boolean keepAlive;

    @NotNull(message = "状态不能为空")
    @Schema(description = "状态")
    private Integer status;

    @NotNull(message = "显示顺序不能为空")
    @Schema(description = "显示顺序")
    private Integer sort;
}
