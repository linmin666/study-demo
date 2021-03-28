package com.linfafa.sort;

public class Solution147 {
    /**
     * 对链表进行插入排序。
     * 从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
     * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode beforeHead = new ListNode(Integer.MIN_VALUE);
        beforeHead.next = head;
        ListNode lastSorted = head; //已排序的最后一个节点
        ListNode unSort = head.next; //待排序节点
        while (unSort != null) {
            if (lastSorted.val <= unSort.val) {//待排序节点已有序
                lastSorted = lastSorted.next;
            } else {
                ListNode i = beforeHead;
                while (unSort.val >= i.next.val) {//从头开始遍历有序序列
                    i = i.next;
                }
                //将待排序节点插入到i.next节点前面
                lastSorted.next = unSort.next;
                unSort.next = i.next;
                i.next = unSort;

            }
            unSort = lastSorted.next;
        }
        return beforeHead.next;
    }


    public static void main(String[] args) {
        ListNode a = new ListNode(4);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(1);
        ListNode d = new ListNode(3);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = null;
        ListNode head = new Solution147().insertionSortList(a);
        ListNode h = head;
        while (h != null) {
            System.out.println(h.val);
            h = h.next;
        }

    }
}
