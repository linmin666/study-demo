package com.linfafa.datastructure.linkedlist;

/**
 * 题目：移除链表元素
 * 难度：简单
 * 题目描述：给你一个链表的头节点head和一个整数val，请你删除链表中所有满足Node.val == val的节点，
 * 并返回新的头节点 。
 *
 * @author linmin
 * @date 2021/5/31
 */
public class Solution203 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            if (curr.val == val) {
                if (curr == head) {//头节点为val
                    curr = curr.next;
                    head = curr;
                } else {//其他节点为val
                    prev.next = curr.next;
                    curr = curr.next;
                }
            } else {
                prev = curr;
                curr = curr.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode a=new ListNode(1);
        ListNode b=new ListNode(2);
        ListNode c=new ListNode(2);
        ListNode d=new ListNode(2);
//        ListNode e=new ListNode(1);
        a.next=b;b.next=c;c.next=d;
//        d.next=e;

        Solution203 s = new Solution203();
        ListNode h = s.removeElements(a, 2);
        while (h!=null){
            System.out.print(h.val);
            h=h.next;
        }
    }
}
