package com.sinvay.oakenshield.datastructure.tree;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 树形结构
 *
 * @param <I> 节点ID类型
 * @param <C> 节点配置
 * @author pizzalord
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
public class TreeNode<I, C> {
    /**
     * 节点ID
     */
    private I id;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 节点处于树的层级
     */
    private int level;

    /**
     * 父节点ID
     */
    private I parentId;

    /**
     * 节点配置
     */
    private C config;

    /**
     * 子节点
     */
    private List<TreeNode<I, C>> children;
}
