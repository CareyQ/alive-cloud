package com.careyq.alive.satoken.core.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 登录用户
 *
 * @author CareyQ
 */
@Data
public class LoginUser {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 菜单权限
     */
    private List<String> permission;

    /**
     * 角色权限
     */
    private List<String> role;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

}
