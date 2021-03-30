package com.linfafa.linkedlist;


public class Solution2 {
    /**
     * 2.l两数相加
     * 给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字0之外，这两个数都不会以0开头。
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int flag = 0;//进位标记
        ListNode res = new ListNode();
        ListNode prev = res;
        //两条链的对应节点都存在
        while (l1 != null && l2 != null) {
            int count=l1.val + l2.val+flag;
            int val=count%10;
            ListNode curr = new ListNode(val);
            flag=0;
            if (count>=10) flag = 1;
            prev.next = curr;
            prev = prev.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        //剩下l1链
        while (l1 != null) {
            System.out.println("node="+l1.val+",flag="+flag);
            int count=l1.val+flag;
            ListNode curr = new ListNode(count%10);
            flag=0;
            if(count>=10)flag=1;
            prev.next = curr;
            prev = prev.next;
            l1 = l1.next;
        }
        //剩下l2链
        while (l2 != null) {
            System.out.println("node="+l2.val+",flag="+flag);
            int count= l2.val+flag;
            ListNode curr = new ListNode(count%10);
            flag=0;
            if(count>=10){
                System.out.println("true");
                flag=1;
            }
            prev.next = curr;
            prev=prev.next;
            l2 = l2.next;
        }

        if(flag>0){
            prev.next=new ListNode(flag);
        }
        return res.next;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(2);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(9);

        ListNode d = new ListNode(5);
        ListNode e = new ListNode(6);
        ListNode f = new ListNode(4);
        ListNode g = new ListNode(9);

        a.next=b;b.next=c;c.next=null;
        d.next=e;e.next=f;f.next=g;g.next=null;

        ListNode res=new Solution2().addTwoNumbers(a,d);
        while (res!=null){
            System.out.println(res.val);
            res=res.next;
        }

    }
}
