package com.careyq.alive.system.convert;

import com.careyq.alive.system.entity.DictData;
import com.careyq.alive.system.vo.DictTypeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 部门相关转换器
 *
 * @author CareyQ
 */
@Mapper
public interface DeptConvert {

    DeptConvert INSTANCE = Mappers.getMapper(DeptConvert.class);

    DictTypeVO convertToVo(DictData dictData);

}
