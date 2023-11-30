package com.careyq.alive.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.core.domain.EntryVO;
import com.careyq.alive.core.enums.CommonStatusEnum;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.system.dto.PostPageDTO;
import com.careyq.alive.system.entity.Post;
import com.careyq.alive.system.entity.User;
import com.careyq.alive.system.mapper.PostMapper;
import com.careyq.alive.system.mapper.UserMapper;
import com.careyq.alive.system.service.PostService;
import com.careyq.alive.system.vo.PostVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.careyq.alive.system.constants.SystemResultCode.*;

/**
 * 岗位服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    private final UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long savePost(PostVO req) {
        // 检查岗位名称是否已存在
        boolean exists = this.lambdaQuery()
                .ne(req.getId() != null, Post::getId, req.getId())
                .eq(Post::getName, req.getName())
                .exists();
        if (exists) {
            throw new CustomException(POST_NAME_DUPLICATE);
        }
        if (req.getId() != null) {
            this.checkPostExists(req.getId());
        }
        Post post = BeanUtil.copyProperties(req, Post.class);
        this.saveOrUpdate(post);
        return post.getId();
    }

    @Override
    public IPage<PostVO> getPostPage(PostPageDTO dto) {
        IPage<Post> page = this.lambdaQuery()
                .like(StrUtil.isNotBlank(dto.getName()), Post::getName, dto.getName())
                .eq(dto.getStatus() != null, Post::getStatus, dto.getStatus())
                .orderByDesc(Post::getId)
                .page(new Page<>(dto.getCurrent(), dto.getSize()));
        return page.convert(e -> PostVO.of(e.getId(), e.getName(), e.getRemark(), e.getStatus(), e.getCreateTime()));
    }

    @Override
    public PostVO getPostDetail(Long id) {
        Post post = this.checkPostExists(id);
        return PostVO.of(post.getId(), post.getName(), post.getRemark(), post.getStatus(), post.getCreateTime());
    }

    @Override
    public void delPost(Long id) {
        boolean exists = userMapper.exists(new LambdaQueryWrapper<User>()
                .apply("FIND_IN_SET({0}, post_ids)", id));
        if (exists) {
            throw new CustomException(POST_HAS_USER);
        }
        this.removeById(id);
    }

    @Override
    public List<EntryVO> getPostSimpleList() {
        return this.lambdaQuery()
                .eq(Post::getStatus, CommonStatusEnum.ENABLE.getStatus())
                .list().stream()
                .map(e -> new EntryVO(e.getId(), e.getName()))
                .toList();
    }

    /**
     * 校验岗位是否存在
     *
     * @param id 岗位 ID
     * @return 岗位
     */
    private Post checkPostExists(Long id) {
        if (id == null) {
            return null;
        }
        Post post = this.getById(id);
        if (post == null) {
            throw new CustomException(POST_NOT_EXISTS);
        }
        return post;
    }

}
