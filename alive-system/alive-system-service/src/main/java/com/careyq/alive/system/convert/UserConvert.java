package com.careyq.alive.system.convert;

import com.careyq.alive.core.domain.EntryVO;
import com.careyq.alive.satoken.core.domain.LoginUser;
import com.careyq.alive.system.entity.Dept;
import com.careyq.alive.system.entity.User;
import com.careyq.alive.system.vo.UserPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户相关转换器
 *
 * @author CareyQ
 */
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    @Mapping(target = "dept", ignore = true)
    UserPageVO convertToPageVo(User user);

    default UserPageVO convertToPageVo(User user, Dept dept) {
        UserPageVO vo = UserConvert.INSTANCE.convertToPageVo(user);
        vo.setDept(new EntryVO(dept.getId(), dept.getName()));
        return vo;
    }

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "permission", ignore = true)
    @Mapping(target = "expireTime", ignore = true)
    LoginUser convertToLoginUser(User user);

    default LoginUser convertToLoginUser(User user, List<String> userRole, List<String> userPermission) {
        LoginUser loginUser = UserConvert.INSTANCE.convertToLoginUser(user);
        loginUser.setRole(userRole);
        loginUser.setPermission(userPermission);
        return loginUser;
    }
}
