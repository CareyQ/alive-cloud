package com.careyq.alive.module.infra.controller.admin;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ZipUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.core.util.ServletUtils;
import com.careyq.alive.module.infra.dto.CodegenImportDTO;
import com.careyq.alive.module.infra.dto.CodegenTablePageDTO;
import com.careyq.alive.module.infra.service.CodegenService;
import com.careyq.alive.module.infra.vo.CodegenDetailVO;
import com.careyq.alive.module.infra.vo.CodegenPreviewVO;
import com.careyq.alive.module.infra.vo.CodegenTablePageVO;
import com.careyq.alive.module.infra.vo.DbTableVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/preview")
    @Operation(summary = "预览生成代码")
    public Result<List<CodegenPreviewVO>> getCodegenPreview(@RequestParam Long tableId) {
        Map<String, String> codes = codegenService.generationCode(tableId);
        return Result.ok(CollUtils.convertList(codes.entrySet(), e -> new CodegenPreviewVO().setFilePath(e.getKey()).setCode(e.getValue())));
    }

    @DeleteMapping("/del")
    @Operation(summary = "删除数据库表")
    public Result<Boolean> delCodegen(@RequestParam Long tableId) {
        codegenService.delCodegen(tableId);
        return Result.ok(true);
    }

    @PostMapping("/update")
    @Operation(summary = "更新表详情")
    public Result<Boolean> updateCodegen(@Validated @RequestBody CodegenDetailVO codegen) {
        codegenService.updateCodegen(codegen);
        return Result.ok(true);
    }

    @GetMapping("/download")
    @Operation(summary = "下载生成代码")
    public void downloadCodegen(@RequestParam Long tableId, HttpServletResponse response) throws IOException {
        Map<String, String> codes = codegenService.generationCode(tableId);
        // 构建 zip 包
        String[] paths = codes.keySet().toArray(new String[0]);
        System.out.println(ArrayUtil.toString(paths));
        ByteArrayInputStream[] ins = codes.values().stream().map(IoUtil::toUtf8Stream).toArray(ByteArrayInputStream[]::new);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipUtil.zip(outputStream, paths, ins);
        // 输出
        ServletUtils.writeAttachment(response, "codegen.zip", outputStream.toByteArray());
    }
}
