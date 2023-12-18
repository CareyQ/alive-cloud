package com.careyq.alive.module.system.convert;

import com.careyq.alive.module.system.entity.Post;
import com.careyq.alive.module.system.vo.PostVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 岗位相关转换器
 *
 * @author CareyQ
 */
@Mapper
public interface PostConvert {

    PostConvert INSTANCE = Mappers.getMapper(PostConvert.class);

    /**
     * 岗位转换为 VO
     *
     * @param post Post
     * @return VO
     */
    PostVO convert(Post post);

}
