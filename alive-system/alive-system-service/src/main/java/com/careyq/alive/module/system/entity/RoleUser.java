package com.careyq.alive.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.careyq.alive.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色关联用户
 *
 * @author CareyQ
 */
@Data
@TableName("system_role_user")
public class RoleUser extends BaseEntity {

    /**
     * 角色 ID
     */
    private Long roleId;

    /**
     * 用户 ID
     */
    private Long userId;

}
