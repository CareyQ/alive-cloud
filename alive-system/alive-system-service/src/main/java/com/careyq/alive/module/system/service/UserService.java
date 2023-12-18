package com.careyq.alive.module.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.mybatis.core.service.IServiceX;
import com.careyq.alive.module.system.dto.UserDTO;
import com.careyq.alive.module.system.dto.UserPageDTO;
import com.careyq.alive.module.system.entity.User;
import com.careyq.alive.module.system.vo.UserPageVO;

/**
 * 系统用户服务
 *
 * @author CareyQ
 */
public interface UserService extends IServiceX<User> {

    /**
     * 手机号是否已存在
     *
     * @param mobile 手机号
     * @param id     用户 ID
     * @return 是否已存在
     */
    boolean mobileIsExist(String mobile, Long id);

    /**
     * 用户名是否已存在
     *
     * @param username 用户名
     * @param id       用户 ID
     * @return 是否已存在
     */
    boolean usernameIsExist(String username, Long id);

    /**
     * 邮箱是否已存在
     *
     * @param email 邮箱
     * @param id    用户 ID
     * @return 是否已存在
     */
    boolean emailIsExist(String email, Long id);

    /**
     * 根据用户名获取
     *
     * @param username 用户名
     * @return 用户
     */
    User getByUsername(String username);

    /**
     * 密码加密
     *
     * @param password 密码
     * @return 解密后的密码
     */
    String encodePassword(String password);

    /**
     * 判断密码是否匹配
     *
     * @param password        未加密密码
     * @param encodedPassword 加密后的密码
     * @return 结果
     */
    boolean isPasswordMatch(String password, String encodedPassword);

    /**
     * 保存用户
     *
     * @param dto 用户信息
     * @return 用户 ID
     */
    Long saveUser(UserDTO dto);

    /**
     * 获取用户分页
     *
     * @param dto 分页请求筛选项
     * @return 用户分页
     */
    IPage<UserPageVO> getUserPage(UserPageDTO dto);

    /**
     * 改变用户状态
     *
     * @param id     用户 ID
     * @param status 状态
     */
    void changeStatus(Long id, Integer status);

    /**
     * 获取用户信息
     *
     * @param id 用户 ID
     * @return 用户
     */
    UserDTO getUserDetail(Long id);

    /**
     * 重置密码
     *
     * @param id       用户 ID
     * @param password 新密码
     */
    void resetPassword(Long id, String password);

    /**
     * 更新最后登录时间
     *
     * @param id 用户 ID
     * @param ip 登录 IP
     */
    void updateLoginTime(Long id, String ip);
}
