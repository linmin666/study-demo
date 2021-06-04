package com.linfafa.datastructure.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * 题目：相交链表
 * 难度：简单
 * 题目描述：给你两个单链表的头节点headA和headB，请你找出并返回
 * 两个单链表相交的起始节点。如果两个链表没有交点，返回null。
 * 题目数据保证整个链式结构中不存在环。
 * <p>
 * 解题思路：若两个链表有交点，那么从表头出发，两个链表都能到达该节点。
 *
 * @author linmin
 * @date 2021/6/4
 */
public class Solution161 {
    //最简单的解法，遍历所有节点，时间复杂度O(n^2)
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        ListNode currA = headA;
        ListNode currB = headB;
        while (currA != null) {
            while (currB != null) {
                System.out.println("currA=" + currA.val + ",currB=" + currB.val);
                if (currB == currA) return currA;
                currB = currB.next;
            }
            currA = currA.next;
            currB = headB;
        }
        return null;
    }

    //哈希表，时间复杂度O(m+n),空间复杂度O(m)
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode currA = headA;
        ListNode currB = headB;
        Set<ListNode> set = new HashSet<>();
        while (currA != null) {
            set.add(currA);
            currA = currA.next;
        }
        while (currB != null) {
            if (set.contains(currB)) return currB;
            currB = currB.next;
        }
        return null;
    }

    /**
     * 🌟🌟
     * 双指针，设链表1未相交的部分节点长度为a，链表2未相交的部分节点长度为b，相交部分长度为c。
     * 若有相交，a+c+b=b+c+a,则指针会在相交节点相遇，
     * 若无，则会在链表1移动m+n次，链表2移动n+m次后，都指向null
     * 时间复杂度O(m+n),空间复杂度O(1)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode currA = headA;
        ListNode currB = headB;
        while (currA != currB) {
            currA = currA == null ? headB : currA.next;
            currB = currB == null ? headA : currB.next;
        }
        return currA;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;
        ListNode m1 = new ListNode(4);
        ListNode m2 = new ListNode(5);
        m1.next = m2;
        m2.next = n3;

        Solution161 s = new Solution161();
        ListNode res = s.getIntersectionNode(n1, m1);
        System.out.println(res);

    }
}
