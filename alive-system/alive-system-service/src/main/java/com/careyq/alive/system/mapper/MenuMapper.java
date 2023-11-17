package com.careyq.alive.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.alive.system.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单权限 mapper
 *
 * @author CareyQ
 * @since 2023-11-17
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
}
