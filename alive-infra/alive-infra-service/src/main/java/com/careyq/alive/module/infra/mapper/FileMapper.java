package com.careyq.alive.module.infra.mapper;

import com.careyq.alive.mybatis.core.mapper.BaseMapperX;
import com.careyq.alive.module.infra.entity.File;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件 mapper
 *
 * @author CareyQ
 */
@Mapper
public interface FileMapper extends BaseMapperX<File> {
}