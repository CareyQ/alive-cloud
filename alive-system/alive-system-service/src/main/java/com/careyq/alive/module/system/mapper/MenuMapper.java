package com.careyq.alive.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.alive.mybatis.core.mapper.LambdaQueryWrapperX;
import com.careyq.alive.module.system.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单权限 mapper
 *
 * @author CareyQ
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据 ID 获取菜单列表
     *
     * @param ids 菜单 ID
     * @return 菜单列表
     */
    default List<Menu> getByIds(List<Long> ids) {
        return this.selectList(new LambdaQueryWrapperX<Menu>().inIfPresent(Menu::getId, ids));
    }
}
