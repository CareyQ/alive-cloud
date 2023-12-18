package com.careyq.alive.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.careyq.alive.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门管理
 *
 * @author CareyQ
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("system_dept")
public class Dept extends BaseEntity {

    /**
     * 根节点
     */
    public static final Long ROOT_ID = 0L;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 父级 ID
     */
    private Long parentId;

    /**
     * 显示排序
     */
    private Integer sort;

    /**
     * 负责人 ID {@link User#getId()}
     */
    private Long managerId;

    /**
     * 联系手机号
     */
    private String mobile;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private Integer status;

}
