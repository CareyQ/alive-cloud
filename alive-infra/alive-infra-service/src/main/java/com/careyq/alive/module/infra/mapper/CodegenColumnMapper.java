package com.careyq.alive.module.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.alive.module.infra.entity.CodegenColumn;
import org.apache.ibatis.annotations.Mapper;

/**
 * 代码生成表字段 mapper
 *
 * @author CareyQ
 */
@Mapper
public interface CodegenColumnMapper extends BaseMapper<CodegenColumn> {
}
