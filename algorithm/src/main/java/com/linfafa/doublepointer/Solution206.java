package com.linfafa.doublepointer;

/**
 * 题目：反转链表
 * 难度：简单
 * 题目描述：给你单链表的头节点head，请你反转链表，并返回反转后的链表
 * @author linmin
 * @date 2021/5/31
 */
public class Solution206 {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode temp = curr;
            curr=curr.next;
            temp.next=prev;
            prev=temp;
        }
        return prev;
    }
    public ListNode reverseList2(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }


    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        a.next = b;
        b.next = c;
        Solution206 s = new Solution206();
        ListNode h = s.reverseList2(a);
        while (h != null) {
            System.out.print(h.val);
            h=h.next;
        }

    }
}
