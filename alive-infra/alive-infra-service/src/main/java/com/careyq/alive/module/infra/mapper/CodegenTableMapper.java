package com.careyq.alive.module.infra.mapper;

import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.module.infra.entity.CodegenTable;
import com.careyq.alive.mybatis.core.mapper.BaseMapperX;
import com.careyq.alive.mybatis.core.query.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成表定义 mapper
 *
 * @author CareyQ
 */
@Mapper
public interface CodegenTableMapper extends BaseMapperX<CodegenTable> {

    /**
     * 查询已存在的表名
     *
     * @param tableNames         表名列表
     * @param dataSourceConfigId 数据源配置编号
     * @return 已存在的表名列表
     */
    default List<String> selectExistTableNames(List<String> tableNames, Long dataSourceConfigId) {
        if (CollUtils.isEmpty(tableNames)) {
            return new ArrayList<>();
        }
        return this.selectList(new LambdaQueryWrapperX<CodegenTable>()
                        .in(CodegenTable::getTableName, tableNames)
                        .eq(CodegenTable::getDataSourceConfigId, dataSourceConfigId)
                        .select(CodegenTable::getTableName))
                .stream().map(CodegenTable::getTableName).toList();
    }
}
