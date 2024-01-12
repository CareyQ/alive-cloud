package com.careyq.alive.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.careyq.alive.core.domain.BaseEntity;
import lombok.Data;

/**
 * 岗位管理
 *
 * @author CareyQ
 */
@Data
@TableName("system_post")
public class Post extends BaseEntity {

    /**
     * 部门名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private Integer status;

}
