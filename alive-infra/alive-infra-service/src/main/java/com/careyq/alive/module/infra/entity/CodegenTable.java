package com.careyq.alive.module.infra.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.careyq.alive.core.domain.BaseEntity;
import com.careyq.alive.module.infra.enums.CodegenSceneEnum;
import lombok.Data;

import java.util.List;

/**
 * 代码生成表定义
 *
 * @author CareyQ
 */
@Data
@TableName("infra_codegen_table")
public class CodegenTable extends BaseEntity {

    /**
     * 数据源配置编号
     */
    private Long dataSourceConfigId;

    /**
     * 生成场景 {@link CodegenSceneEnum}
     */
    private Integer scene;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表描述
     */
    private String tableComment;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 业务名称
     */
    private String businessName;

    /**
     * 类名称
     */
    private String className;

    /**
     * 类描述
     */
    private String classComment;

    /**
     * 作者
     */
    private String author;

    /**
     * 表字段
     */
    @TableField(exist = false)
    private List<CodegenColumn> columns;

}
