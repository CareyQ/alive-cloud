package com.careyq.alive.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.careyq.alive.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典类型
 *
 * @author CareyQ
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("system_dict_type")
public class DictType extends BaseEntity {

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 状态
     */
    private Integer status;
}
