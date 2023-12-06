package com.careyq.alive.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.alive.mybatis.core.mapper.LambdaQueryWrapperX;
import com.careyq.alive.system.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单权限 mapper
 *
 * @author CareyQ
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    default List<Menu> getByIds(List<Long> ids) {
        return this.selectList(new LambdaQueryWrapperX<Menu>().inIfPresent(Menu::getId, ids));
    }
}
