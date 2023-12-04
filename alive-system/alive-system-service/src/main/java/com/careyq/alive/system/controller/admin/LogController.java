package com.careyq.alive.system.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.core.domain.R;
import com.careyq.alive.system.dto.LoginLogPageDTO;
import com.careyq.alive.system.service.LoginLogService;
import com.careyq.alive.system.vo.LoginLogVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志查询
 *
 * @author CareyQ
 */
@Tag(name = "管理后台 - 日志查询")
@RestController
@AllArgsConstructor
@RequestMapping("/log")
public class LogController {

    private final LoginLogService loginLogService;

    @PostMapping("/login-log")
    @Operation(summary = "登录日志")
    public R<IPage<LoginLogVO>> saveUser(@Validated @RequestBody LoginLogPageDTO dto) {
        return R.ok(loginLogService.getLoginLogPage(dto));
    }

}
