package com.linfafa.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个二叉搜索树的根节点root，返回树中任意两不同节点值之间的最小差值 。
 * 树中节点数目在范围 [2, 100] 内
 * 0 <= Node.val <= 10^5
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 *      4
 *    /  \
 *   2   6
 *  / \
 * 1  3
 */
public class Solution783 {
    public int minDiffInBST(TreeNode root) {
        //中序遍历树
        List<Integer> nums = new ArrayList<>();
        inorderRecur(root.left,nums);
        nums.add(root.val);
        inorderRecur(root.right,nums);
        //求升序数组两元素的最小差值
        int min=Integer.MAX_VALUE;
        int index=1;
        while(index<nums.size()){
            min=Math.min(min,nums.get(index)-nums.get(index-1));
            index++;
        }
        return min;
    }

    void inorderRecur(TreeNode node, List<Integer> nums){
        if(node==null)return;
        inorderRecur(node.left,nums);
        nums.add(node.val);
        inorderRecur(node.right,nums);
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(3);
        n1.left=n2;
        n1.right=n3;
        n2.right=null;
        n2.left=null;
        n3.right=null;n3.left=null;

        Solution783 s = new Solution783();
        int res = s.minDiffInBST(n1);
        System.out.println(res);

    }
}
