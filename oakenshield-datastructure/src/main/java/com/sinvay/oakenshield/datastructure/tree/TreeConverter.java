package com.sinvay.oakenshield.datastructure.tree;


import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 树形结构转换器
 *
 * @author pizzalord
 * @since 1.0
 */
public class TreeConverter {
    /**
     * 扁平数组转树形结构
     *
     * @param flatArrayElements 扁平数组转树形结构
     * @param parentId          父级ID
     * @param level             层级
     * @param <I>               ID类型
     * @param <C>               节点配置类型
     * @return 树形结构数组
     */
    public static <I, C> List<TreeNode<I, C>> toTree(List<FlatArrayElement<I, C>> flatArrayElements, I parentId, int level) {
        return flatArrayElements
                .stream()
                .filter(x -> x.getParentId().equals(parentId))
                .map(x -> {
                    TreeNode<I, C> treeNode = new TreeNode<>();
                    return treeNode.toBuilder()
                            .id(x.getId())
                            .name(x.getName())
                            .level(level)
                            .config(x.getConfig())
                            .parentId(x.getParentId())
                            .children(toTree(flatArrayElements, x.getId(), level + 1))
                            .build();
                })
                .collect(Collectors.toList());
    }

    /**
     * 树形结构转扁平数组
     *
     * @param treeNodes 树形结构数组
     * @param <I>       ID类型
     * @param <C>       节点配置类型
     * @return 扁平数组
     */
    public static <I, C> List<FlatArrayElement<I, C>> flat(List<TreeNode<I, C>> treeNodes) {
        List<FlatArrayElement<I, C>> flattenArray = Lists.newArrayList();
        _flat(flattenArray, treeNodes, null);
        return flattenArray;
    }

    private static <I, C> void _flat(List<FlatArrayElement<I, C>> flattenArray, List<TreeNode<I, C>> treeNodes, I parentId) {
        // 最外层的层级
        int level = 1;

        for (TreeNode<I, C> treeNode : treeNodes) {
            I id = treeNode.getId();

            // 加入本层
            flattenArray.add(
                    new FlatArrayElement<I, C>().toBuilder()
                            .id(id)
                            .name(treeNode.getName())
                            .level(level)
                            .parentId(parentId)
                            .config(treeNode.getConfig())
                            .build()
            );

            // 加入下一层
            List<TreeNode<I, C>> children = treeNode.getChildren();
            if (CollectionUtils.isNotEmpty(children)) {
                level++;
                _flat(flattenArray, children, id);
            }
        }
    }
}
