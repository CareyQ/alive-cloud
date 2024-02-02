package com.careyq.alive.module.infra.mapper;

import com.careyq.alive.mybatis.core.mapper.BaseMapperX;
import com.careyq.alive.module.infra.entity.OssConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * 对象存储配置 mapper
 *
 * @author CareyQ
 */
@Mapper
public interface OssConfigMapper extends BaseMapperX<OssConfig> {
}