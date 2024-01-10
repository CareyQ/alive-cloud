package com.careyq.alive.module.system.vo;

import com.careyq.alive.module.system.enums.MenuTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author CareyQ
 */
@Data
@Schema(description = "管理后台 - 菜单列表 VO")
public class RouterVO {

    @Schema(description = "菜单主键")
    private Long id;

    @Schema(description = "父菜单 ID")
    private Long parentId;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "路由地址，菜单类型为菜单或者目录时用")
    private String path;

    @Schema(description = "组件路径，菜单类型为菜单时用")
    private String component;

    @Schema(description = "组件名")
    private String componentName;

    @Schema(description = "菜单图标，菜单类型为菜单或者目录时用")
    private String icon;

    @Schema(description = "是否缓存")
    private Boolean keepAlive;

    /**
     * 菜单类型 {@link MenuTypeEnum}
     */
    @Schema(description = "菜单类型")
    private Integer type;

    @Schema(description = "子路由")
    private List<RouterVO> children;
}
