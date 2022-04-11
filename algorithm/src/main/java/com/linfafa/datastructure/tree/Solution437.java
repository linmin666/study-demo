package com.linfafa.datastructure.tree;

/**
 * 题目：路径总和
 * 难度：中等
 * 描述：给定一个整数二叉树，求有多少条路径节点值和等于给定值。
 * 样例：
 * Input: targetSum = 8 ,tree =
 * 10
 * / \
 * 5  -3
 * / \   \
 * 3   2  11
 * / \   \
 * 3  -2   1
 * Output : 3，路径分别为[[5,3],[]]
 *
 * @author linmin
 * @date 2021/9/12
 */
public class Solution437 {
    /**
     * 递归每个节点时，需要分情况考虑：
     * （1）如果选取该节点加入路径，则之后必须继续加入连续节点，或停止加入节点；
     * （2）如果不选取该节点加入路径，则对其左右节点进行重新进行考虑；
     * 辅函数：用于计算连续加入节点的路径
     */
    int pathSum(TreeNode root, int targetSum) {
        return root != null ?
                pathSumStartWithRoot(root, targetSum) //选取根节点计算
                        + pathSum(root.left, targetSum) //选取左节点计算
                        + pathSum(root.right, targetSum) //选取右节点计算
                : 0;
    }

    int pathSumStartWithRoot(TreeNode root, int targetSum) {
        if (root == null) return 0;
        int count = root.val == targetSum ? 1 : 0;
        count += pathSumStartWithRoot(root.left, targetSum - root.val);
        count += pathSumStartWithRoot(root.right, targetSum - root.val);
        return count;
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(10);
        TreeNode b = new TreeNode(5);
        TreeNode c = new TreeNode(-3);
        TreeNode d = new TreeNode(3);
        TreeNode e = new TreeNode(2);
        TreeNode f = new TreeNode(11);
        TreeNode g = new TreeNode(3);
        TreeNode h = new TreeNode(-2);
        TreeNode i = new TreeNode(1);

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.right = f;
        d.left = g;
        d.right = h;
        e.right = i;

        Solution437 s = new Solution437();
        int targetSum = 8;
        int res = s.pathSum(a, targetSum);
        System.out.println(res);
    }

}
