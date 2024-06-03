package com.careyq.alive.module.system.controller.admin;

import cn.dev33.satoken.annotation.SaIgnore;
import com.careyq.alive.captcha.domain.CaptchaCheck;
import com.careyq.alive.captcha.domain.CaptchaInfo;
import com.careyq.alive.captcha.service.CaptchaService;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.operatelog.core.annotations.OperateLog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 验证码
 *
 * @author CareyQ
 */
@Tag(name = "管理后台 - 验证码")
@RestController
@AllArgsConstructor
@RequestMapping("/system/captcha")
public class CaptchaController {

    private final CaptchaService captchaService;

    @SaIgnore
    @GetMapping("/get")
    @OperateLog(enable = false)
    @Operation(summary = "获取验证码")
    public Result<CaptchaInfo> get() {
        return Result.ok(captchaService.get());
    }

    @SaIgnore
    @PostMapping("/check")
    @OperateLog(enable = false)
    @Operation(summary = "校验验证码")
    public Result<String> check(@RequestBody CaptchaCheck check) {
        return Result.ok(captchaService.check(check));
    }
}
