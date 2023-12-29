package com.careyq.alive.module.infra.convert;

import com.careyq.alive.module.infra.entity.CodegenTable;
import com.careyq.alive.module.infra.vo.CodegenTablePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 代码生成相关转换器
 *
 * @author CareyQ
 */
@Mapper
public interface CodegenConvert {

    CodegenConvert INSTANCE = Mappers.getMapper(CodegenConvert.class);

    /**
     * 表定义转换为分页 VO
     *
     * @param codegenTable   表定义
     * @param dataSourceName 数据源名称
     * @return DTO
     */
    CodegenTablePageVO convert(CodegenTable codegenTable, String dataSourceName);

}
