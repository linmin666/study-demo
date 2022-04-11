package com.linfafa.datastructure.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 题目：删点成林
 * 难度：中等
 * 描述：给定一个整数二叉树和一些整数，求删掉这些整数对应的节点后，剩余的子树。
 * 样例：
 * Input: to_delete = [3,5], tree =
 *           1
 *         /  \
 *        2   3
 *      / \  / \
 *     4  5 6  7
 * Output: [
 *      1
 *     /
 *    2
 *   /
 *  4, 6, 7]
 * @author linmin
 * @date 2021/9/12
 */
public class Solution1110 {
    List<TreeNode> forest=new ArrayList<>();


    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> set=new HashSet<>();
        for(int d:to_delete)set.add(d);

        dfs(null,root,set);
        if(!set.contains(root.val))forest.add(root);
        return forest;
    }

    void dfs(TreeNode parent,TreeNode root,Set<Integer> set) {
        if (root == null) return;

        dfs(root, root.left, set);
        dfs(root, root.right, set);
        if (set.contains(root.val)) {
            if (parent != null && parent.left == root) parent.left = null;
            if (parent != null && parent.right == root) parent.right = null;

            if (root.left != null) forest.add(root.left);
            if (root.right != null) forest.add(root.right);

        }

    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);

        t1.left=t2;t1.right=t3;
        t2.left=t4;t2.right=t5;
        t3.left=t6;t3.right=t7;
        Solution1110 s = new Solution1110();
        int[] to_delete={3,5};
        List<TreeNode> treeNodes = s.delNodes(t1, to_delete);

    }

}
