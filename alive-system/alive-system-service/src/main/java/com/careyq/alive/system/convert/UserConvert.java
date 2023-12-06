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

    /**
     * 转换为用户分页响应体
     *
     * @param user 用户实体
     * @return UserPageVO
     */
    @Mapping(target = "dept", ignore = true)
    UserPageVO convertToPageVo(User user);

    /**
     * 转换为用户分页响应体
     *
     * @param user 用户实体
     * @param dept 部门信息
     * @return UserPageVO
     */
    default UserPageVO convertToPageVo(User user, Dept dept) {
        UserPageVO vo = UserConvert.INSTANCE.convertToPageVo(user);
        vo.setDept(new EntryVO(dept.getId(), dept.getName()));
        return vo;
    }

    /**
     * 转换为登录用户
     *
     * @param user 用户
     * @return 登录用户
     */
    @Mapping(target = "userId", source = "id")
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "permission", ignore = true)
    @Mapping(target = "expireTime", ignore = true)
    LoginUser convertToLoginUser(User user);

    /**
     * 转换为登录用户
     *
     * @param user           用户
     * @param userRole       用户角色
     * @param userPermission 用户权限
     * @return 登录用户
     */
    default LoginUser convertToLoginUser(User user, List<String> userRole, List<String> userPermission) {
        LoginUser loginUser = UserConvert.INSTANCE.convertToLoginUser(user);
        loginUser.setRole(userRole);
        loginUser.setPermission(userPermission);
        return loginUser;
    }
}
