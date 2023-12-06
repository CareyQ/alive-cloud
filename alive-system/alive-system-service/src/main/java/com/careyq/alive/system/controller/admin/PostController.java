package com.careyq.alive.system.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.core.domain.EntryVO;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.system.dto.PostPageDTO;
import com.careyq.alive.system.service.PostService;
import com.careyq.alive.system.vo.PostVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 岗位管理
 *
 * @author CareyQ
 */
@Tag(name = "管理后台 - 岗位管理")
@RestController
@AllArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService deptService;

    @PostMapping("/save")
    @Operation(summary = "保存岗位")
    public Result<Long> savePost(@Validated @RequestBody PostVO req) {
        return Result.ok(deptService.savePost(req));
    }

    @PostMapping("/page")
    @Operation(summary = "获取岗位分页")
    public Result<IPage<PostVO>> getPostPage(@Validated @RequestBody PostPageDTO dto) {
        return Result.ok(deptService.getPostPage(dto));
    }

    @GetMapping("/detail")
    @Operation(summary = "获取岗位详情")
    public Result<PostVO> getPostDetail(@RequestParam Long id) {
        return Result.ok(deptService.getPostDetail(id));
    }

    @DeleteMapping("/del")
    @Operation(summary = "删除岗位")
    public Result<Boolean> delPost(@RequestParam Long id) {
        deptService.delPost(id);
        return Result.ok(true);
    }

    @GetMapping("/simple-list")
    @Operation(summary = "获取岗位，只有启用状态")
    public Result<List<EntryVO>> getPostSimpleList() {
        return Result.ok(deptService.getPostSimpleList());
    }
}
