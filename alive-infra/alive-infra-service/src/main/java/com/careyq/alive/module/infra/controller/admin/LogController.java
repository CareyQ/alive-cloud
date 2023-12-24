package com.careyq.alive.module.infra.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.module.infra.dto.ErrorLogPageDTO;
import com.careyq.alive.module.infra.dto.LoginLogPageDTO;
import com.careyq.alive.module.infra.dto.OperateLogPageDTO;
import com.careyq.alive.module.infra.service.LogService;
import com.careyq.alive.module.infra.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @Operation(summary = "查询登录日志分页")
    public Result<IPage<LoginLogPageVO>> getLoginLogPage(@Validated @RequestBody LoginLogPageDTO dto) {
        return Result.ok(logService.getLoginLogPage(dto));
    }

    @PostMapping("/operate-log")
    @Operation(summary = "查询操作日志分页")
    public Result<IPage<OperateLogPageVO>> getOperateLogPage(@Validated @RequestBody OperateLogPageDTO dto) {
        return Result.ok(logService.getOperateLogPage(dto));
    }

    @GetMapping("/operate-log/detail")
    @Operation(summary = "查询操作日志详情")
    public Result<OperateLogVO> getOperateLogDetail(@RequestParam Long id) {
        return Result.ok(logService.getOperateLogDetail(id));
    }

    @PostMapping("/error-log")
    @Operation(summary = "查询错误日志分页")
    public Result<IPage<ErrorLogPageVO>> getErrorLogPage(@Validated @RequestBody ErrorLogPageDTO dto) {
        return Result.ok(logService.getErrorLogPage(dto));
    }

    @GetMapping("/error-log/detail")
    @Operation(summary = "查询错误日志详情")
    public Result<ErrorLogVO> getErrorLogDetail(@RequestParam Long id) {
        return Result.ok(logService.getErrorLogDetail(id));
    }

    @PutMapping("/error-log/process-status")
    @Operation(summary = "更新错误日志处理状态")
    public Result<Boolean> updateErrorProcessStatus(@RequestParam Long id, @RequestParam Integer processStatus) {
        logService.updateErrorProcessStatus(id, processStatus);
        return Result.ok(true);
    }
}
