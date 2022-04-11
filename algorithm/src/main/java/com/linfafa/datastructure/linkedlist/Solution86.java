package com.linfafa.datastructure.linkedlist;

/**
 * 题目：分隔链表
 * 难度：中等
 * 描述：给你一个链表的头节点head和一个特定值x，请你对链表进行分隔。
 * 使所有小于x的所有节点出现在大于等于x的节点之前。
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 * <p>
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 *
 * @author linmin
 * @date 2022/4/11
 */
public class Solution86 {
    //思路：将链表分成小于x和大于等于x两段，最后拼接在一起
    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        ListNode cur = head; //用于找>=x的节点

        ListNode dummy1 = new ListNode(0);// 用于存<x的节点
        ListNode result1 = dummy1;
        ListNode dummy2 = new ListNode(0);//用于存>=x的节点
        ListNode result2 = dummy2;
        while (cur != null) {
            if (cur.val < x) {
                dummy1.next = cur;
                dummy1 = dummy1.next;
            } else {
                dummy2.next = cur;
                dummy2 = dummy2.next;
            }
            cur = cur.next;
        }

        dummy2.next=null;
        dummy1.next = result2.next;
        return result1.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(4);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(2);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        Solution86 s = new Solution86();
        ListNode result = s.partition(n1, 3);
        while (result != null) {
            System.out.print(result.val + ", ");
            result = result.next;
        }
    }
}
