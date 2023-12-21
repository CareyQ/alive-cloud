package com.careyq.alive.module.infra.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.module.infra.dto.LoginLogPageDTO;
import com.careyq.alive.module.infra.dto.OperateLogPageDTO;
import com.careyq.alive.module.infra.service.LogService;
import com.careyq.alive.module.infra.vo.LoginLogPageVO;
import com.careyq.alive.module.infra.vo.OperateLogPageVO;
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
@RequestMapping("/infra/log")
public class LogController {

    private final LogService logService;

    @PostMapping("/login-log")
    @Operation(summary = "登录日志")
    public Result<IPage<LoginLogPageVO>> getLoginLogPage(@Validated @RequestBody LoginLogPageDTO dto) {
        return Result.ok(logService.getLoginLogPage(dto));
    }

    @PostMapping("/operate-log")
    @Operation(summary = "操作日志")
    public Result<IPage<OperateLogPageVO>> getOperateLogPage(@Validated @RequestBody OperateLogPageDTO dto) {
        return Result.ok(logService.getOperateLogPage(dto));
    }

}
