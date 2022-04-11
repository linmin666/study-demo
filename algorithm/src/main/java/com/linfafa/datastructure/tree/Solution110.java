package com.linfafa.datastructure.tree;

/**
 * 题目：平衡二叉树
 * 难度：简单
 * 描述：给定一个二叉树，判断它是否是高度平衡的二叉树
 * 本题中，一颗高度平衡的二叉树定义为「一个二叉树每个节点的左右两个子树的高度差的绝对值不超过1」。
 * 样例：
 * 3
 * / \
 * 9  20
 * /  \
 * 15  7
 * Input: root=[3,9,20,null,null,15,7]
 * Output: true
 *
 * @author linmin
 * @date 2021/9/12
 */
public class Solution110 {

    /**
     * 解法类似于求树的最大深度，但有两个不同的地方：
     * 1. 我们需要先处理子树的深度再进行比较；
     * 2. 如果在处理子树时发现其已经不平衡了，则返回；
     */
    //主函数
    boolean isBalanced(TreeNode root) {
        return helper(root) != -1;
    }

    //辅函数
    int helper(TreeNode root) {
        if (root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        //left == -1 || right == -1
        // 若在处理子树过程中发现已经不平衡了，则返回
        if (left == -1 || right == -1 || Math.abs(left - right) > 1)
            return -1;
        return 1 + Math.max(left, right);
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

        Solution110 s = new Solution110();
        boolean balanced = s.isBalanced(a);
        System.out.println(balanced);
    }

}
