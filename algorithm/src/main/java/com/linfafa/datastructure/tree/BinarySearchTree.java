package com.linfafa.datastructure.tree;

/**
 * @author linmin
 * @date 2021/9/16
 */
public class BinarySearchTree<T> {
    /**
     * 根节点
     */
    public static TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    /**
     * 查找
     * （1）从root节点开始
     * （2）比当前节点值小，则找其左节点；
     * （3）比当前节点大，则找其右节点；
     * （4）与当前节点值相等，查找到返回
     * （5）查询完毕未找到
     *
     * @param key
     * @return
     */
    public TreeNode search(int key) {
        TreeNode current = root;
        while (current != null && key != current.val) {
            if (current.val > key)
                current = current.left;
            else
                current = current.right;
        }
        return current;
    }

    /**
     * 插入
     * （1）从root节点开始
     * （2）若root为空，root为插入值
     * 循环：
     * （3）若当前节点值大于插入值，找其左节点
     * （4）若当前节点值小于插入值，找其右节点
     *
     * @param key
     * @return
     */
    public TreeNode insert(int key) {
        //新增节点
        TreeNode newNode = new TreeNode(key);
        //当前节点
        TreeNode current = root;
        //上一个节点
        TreeNode parent = null;
        //根节点为空
        if (root == null) {
            root = newNode;
            return newNode;
        }
        while (true) {
            parent = current;
            if (key < current.val) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    return newNode;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    return newNode;
                }
            }
        }
    }

    public TreeNode delete(int key) {
        TreeNode parent = root;
        TreeNode current = root;
        boolean isLeftChild = false;

        while (current.val != key) {
            parent = current;
            if (current.val > key) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null) {
                return current;
            }
        }


        return null;
    }

    public TreeNode getDeleteSuccessor(TreeNode deleteNode) {
        return null;
    }

    public void toString(TreeNode root) {
        if (root != null) {
            toString(root.left);
            System.out.println("value = " + root.val + "->");
            toString(root.right);
        }
    }
}
