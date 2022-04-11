package com.linfafa.datastructure.tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目：从前序与中序遍历序列构造二叉树
 * 难度：中等
 * 描述：给定一颗树的前序遍历preorder与中序遍历inorder。
 * 请构造二叉树并返回其根节点。
 * 样例：
 * Input:          3
 *                / \
 *               9  20
 *                 / \
 *                15  7
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * @author linmin
 * @date 2021/9/13
 */
public class Solution105 {
    /**
     * 递归
     * （1）终止条件：前序和中序数组为空
     * （2）根据前序数组第一个元素，拼出根节点，再将前序数组和中序数组分成两半，
     *     递归的处理前序数组左边和中序数组左边，递归处理前序数组右边和中序数组右边。
     */
    Map<Integer,Integer> inorderMap=new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int size = preorder.length;
        //构造哈希表，快速定位中序遍历的根节点
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, 0, size - 1, 0, size - 1);
    }

    private TreeNode myBuildTree(int[] preorder,int preorderLeft,int preorderRight,int inorderLeft,int inorderRight) {
        if (preorderLeft > preorderRight) return null;
        //前序序列中的第一个节点就是根节点
        int preorderRoot = preorderLeft;
        //在中序序列中定位根节点
        int inorderRoot = inorderMap.get(preorder[preorderRoot]);
        //创建根节点
        TreeNode root = new TreeNode(preorder[preorderRoot]);
        //左子树节点数
        int sizeLeftSubtree = inorderRoot - inorderLeft;
        //递归构造左子树，并连接到根节点
        root.left = myBuildTree(preorder, preorderLeft + 1, preorderLeft + sizeLeftSubtree, inorderLeft, inorderRoot - 1);
        //递归构造右子树，并连接到根节点
        root.right = myBuildTree(preorder, preorderLeft + sizeLeftSubtree + 1, preorderRight, inorderRoot + 1, inorderRight);
        return root;
    }

    public static void main(String[] args) {
        int[] preorder={4,9,20,15,7};
        int[] inorder={9,4,15,20,7};
        Solution105 s = new Solution105();
        TreeNode root = s.buildTree(preorder, inorder);
        System.out.println(root.val);
    }

}
