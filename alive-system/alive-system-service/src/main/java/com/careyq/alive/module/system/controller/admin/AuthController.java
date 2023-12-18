package com.careyq.alive.module.system.controller.admin;

import cn.hutool.core.lang.tree.Tree;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.module.system.dto.LoginDTO;
import com.careyq.alive.module.system.service.AuthService;
import com.careyq.alive.module.system.service.MenuService;
import com.careyq.alive.module.system.service.UserService;
import com.careyq.alive.module.system.vo.LoginVO;
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

    @GetMapping("/check-mobile")
    @Operation(summary = "检查手机号是否已存在")
    public Result<Boolean> checkMobile(@RequestParam String mobile) {
        return Result.ok(userService.mobileIsExist(mobile, null));
    }

    @PostMapping("/login")
    @Operation(summary = "账号密码登录")
    public Result<LoginVO> login(@RequestBody LoginDTO dto) {
        return Result.ok(authService.login(dto));
    }

    @PostMapping("/logout")
    @Operation(summary = "退出登录")
    public Result<Boolean> logout() {
        authService.logout();
        return Result.ok(true);
    }

    @GetMapping("/router")
    @Operation(summary = "获取用户菜单路由")
    public Result<List<Tree<Long>>> getUserMenus() {
        return Result.ok(menuService.getMenuTree(true));
    }
}
