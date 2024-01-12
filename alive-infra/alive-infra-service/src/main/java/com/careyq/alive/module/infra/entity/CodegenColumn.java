package com.careyq.alive.module.infra.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.careyq.alive.core.domain.BaseEntity;
import lombok.Data;

/**
 * 代码生成表字段
 *
 * @author CareyQ
 */
@Data
@TableName("infra_codegen_column")
public class CodegenColumn extends BaseEntity {

    /**
     * 表编号
     */
    private Long tableId;

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 字段类型
     */
    private String dataType;

    /**
     * 字段描述
     */
    private String columnComment;

    /**
     * 是否可以为空
     */
    private Boolean nullable;

    /**
     * 是否主键
     */
    private Boolean primaryKey;

    /**
     * 是否自增
     */
    private Boolean autoIncrement;

    /**
     * Java 属性类型
     */
    private String javaType;

    /**
     * Java 属性名称
     */
    private String javaField;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 是否是创建操作字段
     */
    private Boolean createOperation;

    /**
     * 是否是更新操作字段
     */
    private Boolean updateOperation;

    /**
     * 是否是查询结果字段
     */
    private Boolean queryResult;

    /**
     * 是否是查询条件字段
     */
    private Boolean queryCondition;

    /**
     * 查询时作为条件的类型
     */
    private String queryType;

    /**
     * 前端显示类型
     */
    private String htmlType;

    /**
     * 表名称，用于导入时匹配表
     */
    @TableField(exist = false)
    private String tableName;

}
