package com.careyq.alive.module.infra.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.module.infra.convert.CodegenConvert;
import com.careyq.alive.module.infra.dto.CodegenImportDTO;
import com.careyq.alive.module.infra.dto.CodegenTablePageDTO;
import com.careyq.alive.module.infra.entity.CodegenColumn;
import com.careyq.alive.module.infra.entity.CodegenTable;
import com.careyq.alive.module.infra.mapper.CodegenColumnMapper;
import com.careyq.alive.module.infra.mapper.CodegenTableMapper;
import com.careyq.alive.module.infra.service.CodegenService;
import com.careyq.alive.module.infra.service.DataSourceConfigService;
import com.careyq.alive.module.infra.service.DatabaseTableService;
import com.careyq.alive.module.infra.util.CodegenUtil;
import com.careyq.alive.module.infra.vo.CodegenDetailVO;
import com.careyq.alive.module.infra.vo.CodegenTablePageVO;
import com.careyq.alive.module.infra.vo.DbTableVO;
import com.careyq.alive.mybatis.core.query.LambdaQueryWrapperX;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.careyq.alive.module.infra.constants.InfraResultCode.*;

/**
 * 代码生成服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class CodegenServiceImpl implements CodegenService {

    private final DataSourceConfigService dataSourceConfigService;
    private final DatabaseTableService databaseTableService;
    private final CodegenTableMapper codegenTableMapper;
    private final CodegenColumnMapper codegenColumnMapper;

    @Override
    public List<DbTableVO> getDbTableList(Long dataSourceConfigId, String name, String comment) {
        List<TableInfo> tables = databaseTableService.getTableList(dataSourceConfigId, name, comment);
        Set<String> existsTables = CollUtils.convertSet(codegenTableMapper.selectList(CodegenTable::getDataSourceConfigId, dataSourceConfigId), CodegenTable::getTableName);
        tables.removeIf(table -> existsTables.contains(table.getName()));
        return tables.stream().map(e -> new DbTableVO(e.getName(), e.getComment())).toList();
    }

    @Override
    public IPage<CodegenTablePageVO> getCodegenTablePage(CodegenTablePageDTO dto) {
        Page<CodegenTable> page = codegenTableMapper.selectPage(new Page<>(dto.getCurrent(), dto.getSize()),
                new LambdaQueryWrapperX<CodegenTable>()
                        .likeIfPresent(CodegenTable::getTableName, dto.getTableName())
                        .likeIfPresent(CodegenTable::getTableComment, dto.getTableComment())
                        .dateBetween(CodegenTable::getCreateTime, dto.getStartDate(), dto.getEndDate()));
        if (CollUtils.isEmpty(page.getRecords())) {
            return new Page<>();
        }
        Map<Long, String> dataSourceConfigMap = dataSourceConfigService.getDataSourceConfigMap(CollUtils.convertList(page.getRecords(), CodegenTable::getDataSourceConfigId));
        return page.convert(e -> CodegenConvert.INSTANCE.convert(e, dataSourceConfigMap.get(e.getDataSourceConfigId())));
    }

    @Override
    public List<Long> importCodegenTable(CodegenImportDTO dto) {
        List<TableInfo> tableList = databaseTableService.getTableList(dto.getDataSourceConfigId(), dto.getTableNames().toArray(new String[0]));
        if (tableList.size() != dto.getTableNames().size()) {
            tableList.stream().map(TableInfo::getName).forEach(dto.getTableNames()::remove);
            throw new CustomException(CODEGEN_DB_TABLE_NOT_EXIST, CollUtils.join(dto.getTableNames(), StrUtil.COMMA));
        }
        List<String> existTableNames = codegenTableMapper.selectExistTableNames(dto.getTableNames(), dto.getDataSourceConfigId());
        if (CollUtils.isNotEmpty(existTableNames)) {
            throw new CustomException(CODEGEN_TABLE_IS_EXIST, CollUtils.join(existTableNames, StrUtil.COMMA));
        }

        List<CodegenTable> tables = new ArrayList<>();
        for (TableInfo tableInfo : tableList) {
            validTableInfo(tableInfo);
            tables.add(CodegenUtil.buildTable(tableInfo));
        }
        codegenTableMapper.insertBatch(tables);
        // 表字段设置表编号
        Map<String, Long> tableMap = CollUtils.convertMap(tables, CodegenTable::getTableName, CodegenTable::getId);
        List<CodegenColumn> columns = CollUtils.convertFlatList(tables, CodegenTable::getColumns, List::stream);
        columns.forEach(e -> e.setTableId(tableMap.get(e.getTableName())));
        codegenColumnMapper.insertBatch(columns);
        return CollUtils.convertList(tables, CodegenTable::getId);
    }

    /**
     * 校验 TableInfo 信息
     *
     * @param tableInfo 表信息
     */
    private void validTableInfo(TableInfo tableInfo) {
        if (tableInfo == null) {
            throw new CustomException(CODEGEN_IMPORT_TABLE_NULL);
        }
        if (StrUtil.isEmpty(tableInfo.getComment())) {
            throw new CustomException(CODEGEN_TABLE_COMMENT_IS_NULL);
        }
        if (CollUtils.isEmpty(tableInfo.getFields())) {
            throw new CustomException(CODEGEN_IMPORT_COLUMNS_NULL);
        }
        tableInfo.getFields().forEach(e -> {
            if (StrUtil.isEmpty(e.getComment())) {
                throw new CustomException(CODEGEN_COLUMN_COMMENT_IS_NULL, e.getName());
            }
        });
    }

    @Override
    public CodegenDetailVO getCodegenDetail(Long tableId) {
        CodegenTable table = this.validTable(tableId);
        List<CodegenColumn> columns = this.validColumns(tableId);
        return CodegenConvert.INSTANCE.convert(table, columns);
    }

    /**
     * 校验表定义
     *
     * @param tableId 表编号
     * @return 表定义
     */
    private CodegenTable validTable(Long tableId) {
        CodegenTable table = codegenTableMapper.selectById(tableId);
        if (table == null) {
            throw new CustomException(CODEGEN_TABLE_NOT_EXISTS);
        }
        return table;
    }

    /**
     * 校验表字段
     *
     * @param tableId 表编号
     * @return 表字段列表
     */
    private List<CodegenColumn> validColumns(Long tableId) {
        List<CodegenColumn> columns = codegenColumnMapper.selectList(CodegenColumn::getTableId, tableId);
        if (columns.isEmpty()) {
            throw new CustomException(CODEGEN_COLUMN_NOT_EXISTS);
        }
        return columns;
    }
}
