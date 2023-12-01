package com.careyq.alive.system.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.core.domain.R;
import com.careyq.alive.system.dto.UserDTO;
import com.careyq.alive.system.dto.UserPageDTO;
import com.careyq.alive.system.service.UserService;
import com.careyq.alive.system.vo.UserPageVO;
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
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    @Operation(summary = "保存用户")
    public R<Long> saveUser(@Validated @RequestBody UserDTO dto) {
        return R.ok(userService.saveUser(dto));
    }

    @PostMapping("/page")
    @Operation(summary = "获取用户分页")
    public R<IPage<UserPageVO>> getUserPage(@Validated @RequestBody UserPageDTO dto) {
        return R.ok(userService.getUserPage(dto));
    }

    @GetMapping("/detail")
    @Operation(summary = "获取用户详情")
    public R<UserDTO> getUserDetail(@RequestParam Long id) {
        return R.ok(userService.getUserDetail(id));
    }

    @PutMapping("/status")
    @Operation(summary = "修改用户状态")
    public R<Boolean> changeStatus(@RequestParam Long id, @RequestParam Integer status) {
        userService.changeStatus(id, status);
        return R.ok(true);
    }

    @PutMapping("/password")
    @Operation(summary = "重置密码")
    public R<Boolean> resetPassword(@RequestParam Long id, @RequestParam String password) {
        userService.resetPassword(id, password);
        return R.ok(true);
    }
}
