package com.careyq.alive.module.system.controller.admin;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.lang.tree.Tree;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.module.system.dto.LoginDTO;
import com.careyq.alive.module.system.service.AuthService;
import com.careyq.alive.module.system.service.MenuService;
import com.careyq.alive.module.system.service.UserService;
import com.careyq.alive.module.system.vo.LoginVO;
import com.careyq.alive.operatelog.core.annotations.OperateLog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统认证
 *
 * @author CareyQ
 */
@Tag(name = "管理后台 - 认证")
@RestController
@AllArgsConstructor
@RequestMapping("/system/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final MenuService menuService;

    @SaIgnore
    @GetMapping("/check-mobile")
    @OperateLog(enable = false)
    @Operation(summary = "检查手机号是否已存在")
    public Result<Boolean> checkMobile(@RequestParam String mobile) {
        return Result.ok(userService.mobileIsExist(mobile, null));
    }

    @SaIgnore
    @PostMapping("/login")
    @OperateLog(enable = false)
    @Operation(summary = "账号密码登录")
    public Result<LoginVO> login(@RequestBody LoginDTO dto) {
        return Result.ok(authService.login(dto));
    }

    @SaIgnore
    @PostMapping("/sms-login")
    @OperateLog(enable = false)
    @Operation(summary = "验证码登录")
    public Result<LoginVO> login1(@RequestBody LoginDTO dto) {
        return Result.ok(authService.login(dto));
    }

    @PostMapping("/logout")
    @OperateLog(enable = false)
    @Operation(summary = "退出登录")
    public Result<Boolean> logout() {
        authService.logout();
        return Result.ok(true);
    }

    @GetMapping("/router")
    @OperateLog(enable = false)
    @Operation(summary = "获取用户菜单路由")
    public Result<List<Tree<Long>>> getUserMenus() {
        return Result.ok(menuService.getMenuTree(true));
    }
}
