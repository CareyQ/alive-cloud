package com.careyq.alive.core.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形工具类扩展
 *
 * @author CareyQ
 */
public class TreeUtils extends TreeUtil {

    /**
     * 将指定节点下的子树转为集合，不删除 children
     *
     * @param list 树
     * @param id   id
     * @return List
     */
    public static <T> List<Tree<T>> treeToList(List<Tree<T>> list, T id) {
        if (CollUtil.isEmpty(list)) {
            return new ArrayList<>();
        }
        List<Tree<T>> children = new ArrayList<>();
        for (Tree<T> tree : list) {
            Tree<T> node = TreeUtil.getNode(tree, id);
            if (node == null) {
                continue;
            }
            children.add(node);
            getAllChild(node, children);
            return children;
        }
        return new ArrayList<>();
    }

    private static <T> void getAllChild(Tree<T> tree, List<Tree<T>> children) {
        List<Tree<T>> child = tree.getChildren();
        if (CollUtil.isNotEmpty(child)) {
            children.addAll(child);
            for (Tree<T> subTree : child) {
                getAllChild(subTree, children);
            }
        }
    }
}
