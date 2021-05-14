package com.linfafa.datastructure.tree;

/**
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 * 树中节点数目在范围 [1, 2 * 104] 内
 * <p>
 * 1 <= Node.val <= 105
 * 1 <= low <= high <= 105
 * 所有的Node.val互不相同
 */
public class Solution938 {
    int sum;
    int low;
    int high;

    public int rangeSumBST(TreeNode root, int _low, int _high) {
        low = _low;
        high = _high;
        dfs(root);
        return sum;
    }

    void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        if (node.val >= low && node.val <= high) sum += node.val;
        dfs(node.right);
    }
}
