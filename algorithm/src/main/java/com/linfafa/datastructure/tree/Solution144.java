package com.linfafa.datastructure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author linmin
 * @date 2021/9/16
 */
public class Solution144 {
    //递归
    List<Integer> preorderTraversal1(TreeNode root){
        List<Integer> res=new ArrayList<>();
        myPreorderTraversal(root,res);
        return res;
    }

    void myPreorderTraversal(TreeNode node,List<Integer> res){
        if(node==null)return;
        res.add(node.val);
        myPreorderTraversal(node.left,res);
        myPreorderTraversal(node.right,res);
    }


    //迭代，使用栈
    List<Integer> preorderTraversal(TreeNode root){
        List<Integer> res=new ArrayList<>();
        if(root==null)return res;
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.add(node.val);
            if(node.right!=null)stack.push(node.right);
            if(node.left!=null)stack.push(node.left); //注意，栈首先弹出栈顶元素
            //所以先放right再放left
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode n1=new TreeNode(1);
        TreeNode n2=new TreeNode(2);
        TreeNode n3=new TreeNode(3);
        TreeNode n4=new TreeNode(4);
        TreeNode n5=new TreeNode(5);

        n1.left=n2;n1.right=n3;
        n2.left=n4;n2.right=n5;

        Solution144 s = new Solution144();
        List<Integer> list = s.preorderTraversal1(n1);
        list.forEach(System.out::print);
    }
}
