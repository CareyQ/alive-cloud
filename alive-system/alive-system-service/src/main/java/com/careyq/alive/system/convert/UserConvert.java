package com.careyq.alive.system.convert;

import com.careyq.alive.system.entity.Dept;
import com.careyq.alive.system.entity.User;
import com.careyq.alive.system.vo.UserPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户相关转换器
 *
 * @author CareyQ
 */
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    default UserPageVO convert(User user, Dept dept) {
        return null;
    }
}
