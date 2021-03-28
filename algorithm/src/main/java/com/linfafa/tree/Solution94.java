package com.linfafa.tree;

import com.linfafa.sort.Solution147;

import java.util.ArrayList;
import java.util.List;

public class Solution94 {
    /**
     * 给定一个二叉树的根节点 root ，返回它的中序遍历。
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result=new ArrayList<>();
        if(root==null)return result;
        inorderRecur(root.left,result);
        result.add(root.val);
        inorderRecur(root.right, result);
        return result;
    }
    void inorderRecur(TreeNode node,List<Integer> res){
        if(node==null)return;
        inorderRecur(node.left,res);
        res.add(node.val);
        inorderRecur(node.right,res);
    }

    public static void main(String[] args) {
        TreeNode a=new TreeNode(1);
        TreeNode b=new TreeNode(2);
        TreeNode c=new TreeNode(3);
        a.left=null;
        a.right=b;
        b.left=c;
        b.right=null;
        c.left=null;
        c.right=null;
        List<Integer> res=new Solution94().inorderTraversal(a);
        res.stream().forEach(x-> System.out.println(x));
    }
}
