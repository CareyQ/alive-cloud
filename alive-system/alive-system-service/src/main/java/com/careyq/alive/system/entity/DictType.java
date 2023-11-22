package com.careyq.alive.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.careyq.alive.core.domain.BaseEntity;
import com.careyq.alive.core.enums.CommonStatusEnum;
import com.careyq.alive.system.enums.MenuTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单权限
 *
 * @author CareyQ
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("system_dict_type")
public class DictType extends BaseEntity {

    /**
     * 菜单根节点
     */
    public static final Long ROOT_ID = 0L;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 权限标识，格式：${系统}:${模块}:${操作}
     */
    private String permission;

    /**
     * 菜单类型 {@link MenuTypeEnum}
     */
    private Integer type;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 组件名
     */
    private String componentName;

    /**
     * 状态 {@link CommonStatusEnum}
     */
    private Integer status;

    /**
     * 是否缓存 - 菜单、目录使用
     * 注意：如果开启缓存，则必须填写 {@link #componentName} 属性，否则无法缓存
     */
    private Boolean keepAlive;
}
