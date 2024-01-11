package com.careyq.alive.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.careyq.alive.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色关联菜单
 *
 * @author CareyQ
 */
@Data
@TableName("system_role_menu")
public class RoleMenu extends BaseEntity {

    /**
     * 角色 ID
     */
    private Long roleId;

    /**
     * 菜单 ID
     */
    private Long menuId;

}
