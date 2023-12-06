package com.careyq.alive.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.alive.core.domain.EntryVO;
import com.careyq.alive.mybatis.core.service.IServiceX;
import com.careyq.alive.system.dto.PostPageDTO;
import com.careyq.alive.system.entity.Post;
import com.careyq.alive.system.vo.PostVO;

import java.util.List;

/**
 * 岗位服务
 *
 * @author CareyQ
 */
public interface PostService extends IServiceX<Post> {

    /**
     * 保存岗位
     *
     * @param req 岗位
     * @return 岗位 ID
     */
    Long savePost(PostVO req);

    /**
     * 获取岗位分页
     *
     * @param dto 岗位筛选项
     * @return 岗位分页
     */
    IPage<PostVO> getPostPage(PostPageDTO dto);

    /**
     * 获取岗位详情
     *
     * @param id 岗位 ID
     * @return 岗位
     */
    PostVO getPostDetail(Long id);

    /**
     * 删除岗位
     *
     * @param id 岗位 ID
     */
    void delPost(Long id);

    /**
     * 获取简单岗位列表
     *
     * @return 岗位列表
     */
    List<EntryVO> getPostSimpleList();
}
