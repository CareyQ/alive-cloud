package com.careyq.alive.module.infra.util;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.careyq.alive.core.domain.BaseEntity;
import com.careyq.alive.module.infra.entity.CodegenColumn;
import com.careyq.alive.module.infra.entity.CodegenTable;
import com.careyq.alive.module.infra.enums.CodegenColumnHtmlTypeEnum;
import com.careyq.alive.module.infra.enums.CodegenColumnQueryTypeEnum;
import com.careyq.alive.satoken.AuthHelper;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 代码生成工具类
 *
 * @author CareyQ
 */
public class CodegenUtil {

    /**
     * {@link BaseEntity} 的字段
     */
    public static final Set<String> BASE_ENTITY_FIELDS = new HashSet<>();
    /**
     * 新增操作，不需要传递的字段
     */
    private static final Set<String> CREATE_OPERATION_EXCLUDE_COLUMN = new HashSet<>() {{
        add("id");
    }};
    /**
     * 修改操作，不需要传递的字段
     */
    private static final Set<String> UPDATE_OPERATION_EXCLUDE_COLUMN = new HashSet<>();
    /**
     * 列表操作的条件，不需要传递的字段
     */
    private static final Set<String> QUERY_CONDITION_EXCLUDE_COLUMN = new HashSet<>() {{
        add("id");
    }};
    /**
     * 列表操作的结果，不需要返回的字段
     */
    private static final Set<String> QUERY_RESULT_EXCLUDE_COLUMN = new HashSet<>();
    /**
     * 字段名与 {@link CodegenColumnQueryTypeEnum} 的默认映射
     * 注意，字段的匹配以后缀的方式
     */
    private static final Map<String, CodegenColumnQueryTypeEnum> COLUMN_QUERY_TYPE_MAPPINGS =
            MapUtil.<String, CodegenColumnQueryTypeEnum>builder()
                    .put("name", CodegenColumnQueryTypeEnum.LIKE)
                    .put("time", CodegenColumnQueryTypeEnum.BETWEEN)
                    .put("date", CodegenColumnQueryTypeEnum.BETWEEN)
                    .build();
    /**
     * 字段名与 {@link CodegenColumnHtmlTypeEnum} 的默认映射
     * 注意，字段的匹配以后缀的方式
     */
    private static final Map<String, CodegenColumnHtmlTypeEnum> COLUMN_HTML_TYPE_MAPPINGS =
            MapUtil.<String, CodegenColumnHtmlTypeEnum>builder()
                    .put("status", CodegenColumnHtmlTypeEnum.RADIO)
                    .put("sex", CodegenColumnHtmlTypeEnum.RADIO)
                    .put("type", CodegenColumnHtmlTypeEnum.SELECT)
                    .put("image", CodegenColumnHtmlTypeEnum.IMAGE_UPLOAD)
                    .put("file", CodegenColumnHtmlTypeEnum.FILE_UPLOAD)
                    .put("content", CodegenColumnHtmlTypeEnum.EDITOR)
                    .put("description", CodegenColumnHtmlTypeEnum.EDITOR)
                    .put("demo", CodegenColumnHtmlTypeEnum.EDITOR)
                    .put("time", CodegenColumnHtmlTypeEnum.DATETIME)
                    .put("date", CodegenColumnHtmlTypeEnum.DATETIME)
                    .build();

    static {
        Arrays.stream(ReflectUtil.getFields(BaseEntity.class)).forEach(field -> BASE_ENTITY_FIELDS.add(field.getName()));
        // 处理 OPERATION 相关的字段
        CREATE_OPERATION_EXCLUDE_COLUMN.addAll(BASE_ENTITY_FIELDS);
        UPDATE_OPERATION_EXCLUDE_COLUMN.addAll(BASE_ENTITY_FIELDS);
        QUERY_CONDITION_EXCLUDE_COLUMN.addAll(BASE_ENTITY_FIELDS);
        // 创建时间，还是可能需要传递的
        QUERY_CONDITION_EXCLUDE_COLUMN.remove("createTime");
        QUERY_RESULT_EXCLUDE_COLUMN.addAll(BASE_ENTITY_FIELDS);
        // 创建时间，还是需要返回的
        QUERY_RESULT_EXCLUDE_COLUMN.remove("createTime");
    }

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
                .setAuthor(AuthHelper.getLoginUser().getNickname())
                .setColumns(buildColumn(tableInfo));
        return codegenTable;
    }

    /**
     * 构建表字段列表
     *
     * @param tableInfo 表信息
     * @return 表字段列表
     */
    public static List<CodegenColumn> buildColumn(TableInfo tableInfo) {
        List<CodegenColumn> res = new ArrayList<>();
        for (TableField field : tableInfo.getFields()) {
            TableField.MetaInfo metaInfo = field.getMetaInfo();
            CodegenColumn codegenColumn = new CodegenColumn();
            codegenColumn.setTableName(tableInfo.getName())
                    .setColumnName(field.getColumnName())
                    .setDataType(metaInfo.getJdbcType().name())
                    .setColumnComment(field.getComment())
                    .setNullable(metaInfo.isNullable())
                    .setPrimaryKey(field.isKeyFlag())
                    .setAutoIncrement(field.isKeyIdentityFlag())
                    .setJavaField(field.getPropertyName())
                    .setCreateOperation(CREATE_OPERATION_EXCLUDE_COLUMN.contains(field.getPropertyName()) && !field.isKeyFlag())
                    .setUpdateOperation(UPDATE_OPERATION_EXCLUDE_COLUMN.contains(field.getPropertyName()) || field.isKeyFlag())
                    .setQueryCondition(!QUERY_CONDITION_EXCLUDE_COLUMN.contains(field.getPropertyName()) && !field.isKeyFlag());
            // 设置 JavaType
            String javaType = field.getColumnType().getType();
            codegenColumn.setJavaType(Byte.class.getSimpleName().equals(javaType) ? Integer.class.getSimpleName() : javaType);
            // 设置 QueryType
            COLUMN_QUERY_TYPE_MAPPINGS.entrySet().stream()
                    .filter(entry -> StrUtil.endWithIgnoreCase(field.getPropertyName(), entry.getKey()))
                    .findFirst().ifPresent(entry -> codegenColumn.setQueryType(entry.getValue().getType()));
            if (codegenColumn.getQueryType() == null) {
                codegenColumn.setQueryType(CodegenColumnQueryTypeEnum.EQ.getType());
            }
            // 设置 QueryResult
            codegenColumn.setQueryResult(!QUERY_RESULT_EXCLUDE_COLUMN.contains(field.getPropertyName()));
            // 设置 HtmlType
            processColumnUi(codegenColumn);
            res.add(codegenColumn);
        }
        if (!tableInfo.isHavePrimaryKey()) {
            res.getFirst().setPrimaryKey(true);
        }
        return res;
    }

    /**
     * 设置 HtmlType
     *
     * @param column CodegenColumn
     */
    private static void processColumnUi(CodegenColumn column) {
        // 基于后缀进行匹配
        COLUMN_HTML_TYPE_MAPPINGS.entrySet().stream()
                .filter(entry -> StrUtil.endWithIgnoreCase(column.getJavaField(), entry.getKey()))
                .findFirst().ifPresent(entry -> column.setHtmlType(entry.getValue().getType()));
        // 如果是 Boolean 类型时，设置为 radio 类型.
        if (Boolean.class.getSimpleName().equals(column.getJavaType())) {
            column.setHtmlType(CodegenColumnHtmlTypeEnum.RADIO.getType());
        }
        // 如果是 LocalDateTime 类型，则设置为 datetime 类型
        if (LocalDateTime.class.getSimpleName().equals(column.getJavaType())) {
            column.setHtmlType(CodegenColumnHtmlTypeEnum.DATETIME.getType());
        }
        // 兜底，设置默认为 input 类型
        if (column.getHtmlType() == null) {
            column.setHtmlType(CodegenColumnHtmlTypeEnum.INPUT.getType());
        }
    }
}
