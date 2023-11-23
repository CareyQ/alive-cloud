package com.careyq.alive.system.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.core.domain.EntryVO;
import com.careyq.alive.core.domain.R;
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
@RequestMapping("/dept")
public class PostController {

    private final PostService deptService;

    @PostMapping("/save")
    @Operation(summary = "保存岗位")
    public R<Long> savePost(@Validated @RequestBody PostVO req) {
        return R.ok(deptService.savePost(req));
    }

    @PostMapping("/page")
    @Operation(summary = "获取岗位分页")
    public R<IPage<PostVO>> getPostPage(@Validated @RequestBody PostPageDTO dto) {
        return R.ok(deptService.getPostPage(dto));
    }

    @GetMapping("/detail")
    @Operation(summary = "获取岗位详情")
    public R<PostVO> getPostDetail(@RequestParam Long id) {
        return R.ok(deptService.getPostDetail(id));
    }

    @DeleteMapping("/del")
    @Operation(summary = "删除岗位")
    public R<Boolean> delPost(@RequestParam Long id) {
        deptService.delPost(id);
        return R.ok(true);
    }

    @GetMapping("/simple-list")
    @Operation(summary = "获取岗位，只有启用状态")
    public R<List<EntryVO>> getPostSimpleList() {
        return R.ok(deptService.getPostSimpleList());
    }
}
