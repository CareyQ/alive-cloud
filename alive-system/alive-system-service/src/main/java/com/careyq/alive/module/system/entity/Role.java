package com.careyq.alive.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.careyq.alive.core.domain.BaseEntity;
import lombok.Data;

/**
 * 角色信息
 *
 * @author CareyQ
 */
@Data
@TableName("system_role")
public class Role extends BaseEntity {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色标识
     */
    private String code;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否是默认
     */
    private Boolean isDefault;

}
