package com.careyq.alive.system.convert;

import com.careyq.alive.system.entity.Role;
import com.careyq.alive.system.vo.RoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 角色相关转换器
 *
 * @author CareyQ
 */
@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    /**
     * 角色转换为 VO
     *
     * @param role Role
     * @return VO
     */
    RoleVO convert(Role role);

}
