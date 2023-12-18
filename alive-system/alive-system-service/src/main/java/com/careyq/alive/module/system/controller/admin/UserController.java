package com.careyq.alive.module.system.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.module.system.dto.UserDTO;
import com.careyq.alive.module.system.dto.UserPageDTO;
import com.careyq.alive.module.system.service.UserService;
import com.careyq.alive.module.system.vo.UserPageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author CareyQ
 */
@Tag(name = "管理后台 - 用户管理")
@RestController
@AllArgsConstructor
@RequestMapping("/system/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    @Operation(summary = "保存用户")
    public Result<Long> saveUser(@Validated @RequestBody UserDTO dto) {
        return Result.ok(userService.saveUser(dto));
    }

    @PostMapping("/page")
    @Operation(summary = "获取用户分页")
    public Result<IPage<UserPageVO>> getUserPage(@Validated @RequestBody UserPageDTO dto) {
        return Result.ok(userService.getUserPage(dto));
    }

    @GetMapping("/detail")
    @Operation(summary = "获取用户详情")
    public Result<UserDTO> getUserDetail(@RequestParam Long id) {
        return Result.ok(userService.getUserDetail(id));
    }

    @PutMapping("/status")
    @Operation(summary = "修改用户状态")
    public Result<Boolean> changeStatus(@RequestParam Long id, @RequestParam Integer status) {
        userService.changeStatus(id, status);
        return Result.ok(true);
    }

    @PutMapping("/password")
    @Operation(summary = "重置密码")
    public Result<Boolean> resetPassword(@RequestParam Long id, @RequestParam String password) {
        userService.resetPassword(id, password);
        return Result.ok(true);
    }
}
