package com.careyq.alive.system.convert;

import com.careyq.alive.system.entity.Post;
import com.careyq.alive.system.vo.PostVO;
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

    PostVO convert(Post post);

}
