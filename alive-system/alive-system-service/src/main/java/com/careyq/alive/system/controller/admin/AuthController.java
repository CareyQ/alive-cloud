package com.careyq.alive.system.controller.admin;

import cn.hutool.core.lang.tree.Tree;
import com.careyq.alive.core.domain.R;
import com.careyq.alive.system.dto.LoginDTO;
import com.careyq.alive.system.service.AuthService;
import com.careyq.alive.system.service.MenuService;
import com.careyq.alive.system.service.UserService;
import com.careyq.alive.system.vo.LoginVO;
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
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final MenuService menuService;

    @GetMapping("/check-mobile")
    @Operation(summary = "检查手机号是否已存在")
    public R<Boolean> checkMobile(@RequestParam String mobile) {
        return R.ok(userService.mobileIsExist(mobile, null));
    }

    @PostMapping("/login")
    @Operation(summary = "账号密码登录")
    public R<LoginVO> login(@RequestBody LoginDTO dto) {
        return R.ok(authService.login(dto));
    }

    @GetMapping("/router")
    @Operation(summary = "获取用户菜单路由")
    public R<List<Tree<Long>>> getUserMenus() {
        return R.ok(menuService.getMenuTree(null, true));
    }
}
