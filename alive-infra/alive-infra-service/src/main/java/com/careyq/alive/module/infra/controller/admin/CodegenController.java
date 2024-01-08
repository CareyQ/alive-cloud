package com.careyq.alive.module.infra.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.module.infra.dto.CodegenImportDTO;
import com.careyq.alive.module.infra.dto.CodegenTablePageDTO;
import com.careyq.alive.module.infra.service.CodegenService;
import com.careyq.alive.module.infra.vo.CodegenDetailVO;
import com.careyq.alive.module.infra.vo.CodegenTablePageVO;
import com.careyq.alive.module.infra.vo.DbTableVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 代码生成
 *
 * @author CareyQ
 */
@Tag(name = "管理后台 - 代码生成")
@RestController
@AllArgsConstructor
@RequestMapping("/infra/codegen")
public class CodegenController {

    private final CodegenService codegenService;

    @GetMapping("/db/table/list")
    @Operation(summary = "获取数据库表列表")
    public Result<List<DbTableVO>> getDbTableList(@RequestParam Long dataSourceConfigId, @RequestParam(required = false) String name, @RequestParam(required = false) String comment) {
        return Result.ok(codegenService.getDbTableList(dataSourceConfigId, name, comment));
    }

    @PostMapping("/table/page")
    @Operation(summary = "获取表定义")
    public Result<IPage<CodegenTablePageVO>> getCodegenTablePage(@Validated @RequestBody CodegenTablePageDTO dto) {
        return Result.ok(codegenService.getCodegenTablePage(dto));
    }

    @PostMapping("/import")
    @Operation(summary = "导入数据库表结构")
    public Result<List<Long>> importCodegenTable(@Validated @RequestBody CodegenImportDTO dto) {
        return Result.ok(codegenService.importCodegenTable(dto));
    }

    @GetMapping("/detail")
    @Operation(summary = "获取表详情")
    public Result<CodegenDetailVO> getCodegenDetail(@RequestParam Long tableId) {
        return Result.ok(codegenService.getCodegenDetail(tableId));
    }
}
