package com.linfafa.datastructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 题目：二叉树的层平均值
 * 难度：简单
 * 描述：给定一个非空二叉树，返回一个由每层节点平均值组成的数组。
 * 节点范围在32位有符号整数范围内
 * 样例：
 * Input:        3
 *              / \
 *             9  20
 *               / \
 *              15 7
 * Output: [3, 14.5, 11]
 * @author linmin
 * @date 2021/9/13
 */
public class Solution637 {
    /**
     * 广度优先遍历
     * （1）初始化时，将根节点加入队列；
     * （2）每一轮遍历时，将队列中的节点全部取出，计算这些节点的平均值，
     *     并将这些节点的非空子节点加入队列；
     */

    List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            double sum = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val; //求和
                //添加子节点
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);

            }
            res.add(sum / size);
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(15);
        TreeNode t5 = new TreeNode(7);
        t1.left=t2;t1.right=t3;
        t3.left=t4;t3.right=t5;

        Solution637 s = new Solution637();
        List<Double> res = s.averageOfLevels(t1);
        res.forEach(System.out::println);
    }
}
