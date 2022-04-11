package com.linfafa.datastructure.linkedlist;

/**
 * 题目：合并两个有序链表
 * 难度；简单
 * 描述：将两个升序链表合并为一个新的「升序」链表并返回。
 * 新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * <p>
 * 时间复杂度：O(m+n)
 * 空间复杂度：O(1)
 * @author linmin
 * @date 2021/8/17
 */
public class Solution21 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode curL1 = l1, curL2 = l2;
        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead;
        //两个链表当前节点都存在
        while (curL1 != null && curL2 != null) {
            //比较两个节点大小
            if (curL1.val <= curL2.val) {
                //l1放入新链表,指针后移
                cur.next = curL1;
                curL1 = curL1.next;
            } else {
                //l2放入新链表,指针后移
                cur.next = curL2;
                curL2 = curL2.next;
            }
            cur = cur.next;
        }
        //l1链表剩余节点
        while (curL1 != null) {
            cur.next = curL1;
            curL1 = curL1.next;
            cur = cur.next;
        }

        //l2链表剩余节点
        while (curL2 != null) {
            cur.next = curL2;
            curL2 = curL2.next;
            cur = cur.next;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode l1A = new ListNode(1);
        ListNode l1B = new ListNode(2);
        ListNode l1C = new ListNode(4);
        l1A.next = l1B;
        l1B.next = l1C;

        ListNode l2A = new ListNode(1);
        ListNode l2B = new ListNode(3);
        ListNode l2C = new ListNode(4);
        l2A.next = l2B;
        l2B.next = l2C;

        Solution21 s = new Solution21();

        ListNode res = s.mergeTwoLists(l1A, l2A);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }

    }
}
