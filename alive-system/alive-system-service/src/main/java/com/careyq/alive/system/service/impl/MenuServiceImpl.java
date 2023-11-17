package com.careyq.alive.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.alive.system.entity.Menu;
import com.careyq.alive.system.enums.MenuTypeEnum;
import com.careyq.alive.system.mapper.MenuMapper;
import com.careyq.alive.system.service.MenuService;
import com.careyq.alive.system.vo.MenuVO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 菜单权限服务实现
 *
 * @author CareyQ
 * @since 2023-11-17
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<MenuVO> getMenuTree(Set<Long> roleIds) {
        List<Menu> menus = new ArrayList<>();
        if (CollUtil.isEmpty(roleIds)) {
            menus = this.lambdaQuery()
                    .ne(Menu::getType, MenuTypeEnum.BUTTON.getType())
                    .list();
        } else {
            // todo
        }
        return MenuServiceImpl.buildMenuTree(menus);
    }

    /**
     * 构建菜单树
     *
     * @param menus 菜单列表
     * @return 菜单树
     */
    private static List<MenuVO> buildMenuTree(List<Menu> menus) {
        menus.sort(Comparator.comparing(Menu::getSort));

        Map<Long, MenuVO> treeNodeMap = menus.stream().map(e -> {
            MenuVO vo = new MenuVO();
            vo.setId(e.getId())
                    .setParentId(e.getParentId())
                    .setName(e.getName())
                    .setPath(e.getPath())
                    .setComponent(e.getComponent())
                    .setComponentName(e.getComponentName())
                    .setIcon(e.getIcon())
                    .setKeepAlive(e.getKeepAlive());
            return vo;
        }).collect(Collectors.toMap(MenuVO::getId, Function.identity(), (key1, key2) -> key1, LinkedHashMap::new));

        treeNodeMap.values().stream().filter(node -> !node.getParentId().equals(Menu.ROOT_ID)).forEach(childNode -> {
            MenuVO parentNode = treeNodeMap.get(childNode.getParentId());
            if (parentNode == null) {
                return;
            }
            if (parentNode.getChildren() == null) {
                parentNode.setChildren(new ArrayList<>());
            }
            parentNode.getChildren().add(childNode);
        });
        return treeNodeMap.values().stream()
                .filter(e -> Menu.ROOT_ID.equals(e.getParentId()))
                .collect(Collectors.toList());
    }
}
