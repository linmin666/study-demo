package com.linfafa.datastructure.tree;

/**
 * 题目：对称二叉树
 * 难度：简单
 * 描述：判断一个二叉树是否对称
 * 样例：
 * Input:     1
 *           / \
 *          2   2
 *         / \ / \
 *        3  4 4  3
 * Output: true
 *
 * @author linmin
 * @date 2021/9/12
 */
public class Solution101 {
    /**
     * 判断一个树是否对称等价于判断左右子树是否对称。
     * 可将该类题的解法"四步法"：
     * （1）如果两个子树都为null，则它们相等或对称；
     * （2）如果两个子树只有一个为null，则它们不相等或不对称；
     * （3）如果两个子树根节点的值不相等，则它们不相等或不对称；
     * （4）根据相等或对称要求，进行递归处理；
     */
    boolean isSummertric(TreeNode root){
        return root == null ? isSummertric(root.left,root.right) : true;
    }

    boolean isSummertric(TreeNode left,TreeNode right){
        if(left == null && right == null)
            return true;
        if(left == null || right == null)
            return false;
        if(left.val != right.val)
            return false;
        return isSummertric(left.left,right.right)
                && isSummertric(left.right,right.left);
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(2);
        TreeNode d = new TreeNode(3);
        TreeNode e = new TreeNode(4);
        TreeNode f = new TreeNode(4);
        TreeNode g = new TreeNode(3);

        a.left=b;a.right=c;
        b.left=d;b.right=e;
        c.left=f;c.right=g;

        Solution101 s = new Solution101();
        boolean res = s.isSummertric(a);
        System.out.println(res);
    }
}
