package com.careyq.alive.system.controller.admin;

import cn.hutool.core.lang.tree.Tree;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.system.dto.DeptDTO;
import com.careyq.alive.system.dto.DeptSearchDTO;
import com.careyq.alive.system.service.DeptService;
import com.careyq.alive.system.vo.DeptVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理
 *
 * @author CareyQ
 */
@Tag(name = "管理后台 - 部门管理")
@RestController
@AllArgsConstructor
@RequestMapping("/dept")
public class DeptController {

    private final DeptService deptService;

    @PostMapping("/save")
    @Operation(summary = "保存部门")
    public Result<Long> saveDept(@Validated @RequestBody DeptDTO dto) {
        return Result.ok(deptService.saveDept(dto));
    }

    @PostMapping("/list")
    @Operation(summary = "获取部门列表")
    public Result<List<Tree<Long>>> getDeptList(@Validated @RequestBody DeptSearchDTO dto) {
        return Result.ok(deptService.getDeptList(dto));
    }

    @GetMapping("/detail")
    @Operation(summary = "获取部门详情")
    public Result<DeptVO> getDeptDetail(@RequestParam Long id) {
        return Result.ok(deptService.getDeptDetail(id));
    }

    @DeleteMapping("/del")
    @Operation(summary = "删除部门")
    public Result<Boolean> delDept(@RequestParam Long id) {
        deptService.delDept(id);
        return Result.ok(true);
    }

    @GetMapping("/simple-list")
    @Operation(summary = "获取部门，只有启用状态")
    public Result<List<Tree<Long>>> getDeptSimpleList() {
        return Result.ok(deptService.getDeptSimpleList());
    }
}
