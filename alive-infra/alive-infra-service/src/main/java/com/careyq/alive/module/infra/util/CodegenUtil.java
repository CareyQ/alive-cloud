package com.careyq.alive.module.infra.util;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.careyq.alive.module.infra.entity.CodegenColumn;
import com.careyq.alive.module.infra.entity.CodegenTable;
import com.careyq.alive.satoken.AuthHelper;

import java.util.List;

/**
 * 代码生成工具类
 *
 * @author CareyQ
 */
public class CodegenUtil {

    /**
     * 构建表定义
     *
     * @param tableInfo 表信息
     * @return 表定义
     */
    public static CodegenTable buildTable(TableInfo tableInfo) {
        String tableName = tableInfo.getName();
        String prefix = StrUtil.subBefore(tableName, StrUtil.UNDERLINE, false).toLowerCase();
        String suffix = StrUtil.subAfter(tableName, StrUtil.UNDERLINE, false).toLowerCase();

        CodegenTable codegenTable = new CodegenTable();
        codegenTable.setTableName(tableName)
                .setTableComment(tableInfo.getComment())
                .setModuleName(prefix)
                .setBusinessName(suffix)
                .setClassName(StrUtil.toCamelCase(suffix))
                .setClassComment(tableInfo.getComment())
                .setAuthor(AuthHelper.getLoginUser().getNickname());
        return codegenTable;
    }

    public static CodegenColumn buildColumn(TableInfo tableInfo) {
        for (TableField field : tableInfo.getFields()) {
            TableField.MetaInfo metaInfo = field.getMetaInfo();
            CodegenColumn codegenColumn = new CodegenColumn();
            codegenColumn.setTableName(tableInfo.getName())
            .setColumnName(field.getColumnName())
            .setDataType(field.getColumnType().getType())
            .setColumnComment(field.getComment())
            .setNullable(field.getMetaInfo())
            .setPrimaryKey()
            .setAutoIncrement()
            .setJavaType()
            .setJavaField()
            .setDictType()
            .setCreateOperation()
            .setUpdateOperation()
            .setQueryResult()
            .setQueryCondition()
            .setQueryType()
            .setHtmlType();

        }


        return codegenColumn;
    }
}
