package com.careyq.alive.system.controller.admin;

import com.careyq.alive.core.domain.R;
import com.careyq.alive.system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统认证
 *
 * @author CareyQ
 * @since 2023-11-14
 */
@Tag(name = "管理后台 - 认证")
@RestController
@AllArgsConstructor
@RequestMapping("/system/auth")
public class AuthController {

    private final UserService userService;

    @GetMapping("/check-mobile")
    @Operation(summary = "检查手机号是否已存在")
    public R<Boolean> checkMobile(@RequestParam String mobile) {
        return R.ok(userService.mobileIsExist(mobile));
    }
}
