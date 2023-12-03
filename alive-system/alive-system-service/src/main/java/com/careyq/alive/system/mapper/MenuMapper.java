package com.careyq.alive.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.system.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限 mapper
 *
 * @author CareyQ
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    default List<Menu> getByIds(List<Long> ids) {
        if (CollUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }
        return this.selectList(new LambdaQueryWrapper<Menu>().in(Menu::getId, ids));
    }
}
