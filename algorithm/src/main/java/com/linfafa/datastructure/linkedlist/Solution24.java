package com.linfafa.datastructure.linkedlist;

import java.util.List;

/**
 * 题目：两两交换链表中的节点
 * 难度：中等
 * 描述：给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 不能只是单纯的改变节点内部的值，而是需要时机的进行节点交换。
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 * @author linmin
 * @date 2021/8/17
 */
public class Solution24 {

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        recurisve(dummy);
        return dummy.next;
    }

    //将node节点后的两个节点交换
    void recurisve(ListNode node) {
        if (node == null) return;
        ListNode n1 = node.next;
        if (n1 == null) return;
        ListNode n2 = n1.next;
        if (n2 == null) return;

        //swap node n1 and node n2
        node.next = n2;
        n1.next = n2.next;
        n2.next = n1;

        recurisve(n1);
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        //l1.next=l2;//l2.next=l3;//l3.next=l4;

        Solution24 s = new Solution24();
        ListNode res = s.swapPairs(l1);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}
