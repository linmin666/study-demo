package com.linfafa.datastructure.tree;

/**
 * 题目：二叉树的直径
 * 难度：简单🌟
 * 描述：给定一颗二叉树，你需要计算它的直径长度。一颗二叉树的直径长度是任意两个节点路径
 * 长度中的最大值。这条路径可能穿过也可能不穿过根节点。
 * 样例：
 * Input:
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 * Output: 3,路径为[4,2,1,3]或[5,2,1,3]
 *
 * @author linmin
 * @date 2021/9/12
 */
public class Solution543 {


    /**
     * 在处理某个子树时，我们更新的最长直径值和递归返回的值是不同的。
     * 因为待更新的最长直径是经过该子树根节点的最长直径（两侧长度）；
     * 而函数返回值是以该子树根节点为端点的最长直径值（即一侧长度），
     * 使用这样的返回值才可以通过递归更新父节点的最长直径值。
     */
    int diameter;
    int diameterOfBinaryTree(TreeNode root) {
        helper(root);
        return diameter;
    }

    int helper(TreeNode node) {
        if (node == null) return 0;
//        System.out.println("node = "+node.val);
        int left = helper(node.left);
        int right = helper(node.right);
        diameter = Math.max(left + right, diameter);// 两次长度
//        System.out.println("diameter = "+diameter);
        return Math.max(left, right) + 1; //单侧最长直径值
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);
        a.left=b;a.right=c;
        b.left=d;b.right=e;


        Solution543 s = new Solution543();
        int res = s.diameterOfBinaryTree(a);
        System.out.println(res);

    }
}

/**
 *
 * helper(Node(1),diameter)
 * diameter = max(left + right,diameter) => 3
 * -------------------
 * |return 2 + 1 => 3|
 * -------------------
 * =>
 * left:
 * left = helper(Node(2),diameter) => 2
 * diameter = max(left + right,diameter) => 2
 * --------------------
 * |return 1 + 1 => 2 |
 * --------------------
 * =>
 * left = helper(Node(4),diameter) => 1
 * right = helper(Node(5),diameter) => 1
 * diameter = max(left + right,diameter) => 1
 *
 * =>
 * left = helper(null,diameter) => 0
 * right = helper(null,diameter) => 0
 * diameter = max(left + right,diameter) => 0
 *
 * left = helper(null,diameter) => 0
 * right = helper(null,diameter) => 0
 * diameter = max(left + right,diameter) => 0
 *
 *
 * right:
 * right = helper(Node(3),diameter)
 * =>
 * left = helper(null,diameter) => 0
 * right = helper(null,diameter) => 0
 * diameter = max(left + right,diameter) => 0
 * ------------
 * | return 0 + 1 => 1;|
 * ------------
 *
 *
 *
 */
