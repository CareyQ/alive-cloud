package com.careyq.alive.module.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.careyq.alive.core.domain.EntryVO;
import com.careyq.alive.core.enums.CommonStatusEnum;
import com.careyq.alive.core.exception.CustomException;
import com.careyq.alive.module.system.constants.SystemResultCode;
import com.careyq.alive.mybatis.core.service.impl.ServiceImplX;
import com.careyq.alive.module.system.convert.PostConvert;
import com.careyq.alive.module.system.dto.PostPageDTO;
import com.careyq.alive.module.system.entity.Post;
import com.careyq.alive.module.system.entity.User;
import com.careyq.alive.module.system.mapper.PostMapper;
import com.careyq.alive.module.system.mapper.UserMapper;
import com.careyq.alive.module.system.service.PostService;
import com.careyq.alive.module.system.vo.PostVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 岗位服务实现
 *
 * @author CareyQ
 */
@Service
@AllArgsConstructor
public class PostServiceImpl extends ServiceImplX<PostMapper, Post> implements PostService {

    private final UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long savePost(PostVO req) {
        // 检查岗位名称是否已存在
        boolean exists = this.lambdaQueryX()
                .neIfPresent(Post::getId, req.getId())
                .eq(Post::getName, req.getName())
                .exists();
        if (exists) {
            throw new CustomException(SystemResultCode.POST_NAME_DUPLICATE);
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
        IPage<Post> page = this.lambdaQueryX()
                .likeIfPresent(Post::getName, dto.getName())
                .eqIfPresent(Post::getStatus, dto.getStatus())
                .orderByDesc(Post::getId)
                .page(new Page<>(dto.getCurrent(), dto.getSize()));
        return page.convert(PostConvert.INSTANCE::convert);
    }

    @Override
    public PostVO getPostDetail(Long id) {
        Post post = this.checkPostExists(id);
        return PostConvert.INSTANCE.convert(post);
    }

    @Override
    public void delPost(Long id) {
        boolean exists = userMapper.exists(new LambdaQueryWrapper<User>()
                .apply("FIND_IN_SET({0}, post_ids)", id));
        if (exists) {
            throw new CustomException(SystemResultCode.POST_HAS_USER);
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
            throw new CustomException(SystemResultCode.POST_NOT_EXISTS);
        }
        return post;
    }

}
