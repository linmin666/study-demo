package com.linfafa.datastructure.tree;

/**
 * 题目：二叉树的最大深度
 * 难度：简单
 * 描述：求一个二叉树的最大深度
 * 样例：
 * Input:
 * 3
 * / \
 * 9  20
 * / \
 * 15 7
 * Output: 3
 *
 * @author linmin
 * @date 2021/9/12
 */
public class Solution104 {
    //递归求解
    int maxDepth(TreeNode root) {
        return root != null ? 1 + Math.max(maxDepth(root.left), maxDepth(root.right)) : 0;
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(3);
        TreeNode b = new TreeNode(9);
        TreeNode c = new TreeNode(20);
        TreeNode d = new TreeNode(15);
        TreeNode e = new TreeNode(7);
        a.left = b;
        a.right = c;
        c.left = d;
        c.right = e;

        Solution104 s = new Solution104();
        int depth = s.maxDepth(a);
        System.out.println(depth);
    }
}
