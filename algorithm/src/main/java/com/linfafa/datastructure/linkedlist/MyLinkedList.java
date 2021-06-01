package com.linfafa.datastructure.linkedlist;

/**
 * 题目：设计链表
 * 难度：中等
 * 题目描述：设计链表的实现。您可以选择使用单链表或双链表。
 * 单链表中的节点应该具有两个属性：val和next。val是当前节点的值，
 * next是指向下一个节点的指针/引用。如果要使用双向链表，
 * 则还需要一个属性prev以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 * <p>
 * 在链表类中实现这些功能：
 * get(index)：获取链表中第index个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为val的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为val的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第index个节点之前添加值为val的节点。如果index等于链表的长度，
 * 则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引index有效，则删除链表中的第index个节点。
 *
 * @author linmin
 * @date 2021/5/31
 */
public class MyLinkedList {
    private int size;
    private ListNode dummyHead;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        dummyHead = new ListNode(0);//虚拟头节点
        size = 0;

    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index > size - 1 || index < 0) return -1;
        ListNode curr = dummyHead.next;
        while (index > 0) {
            curr = curr.next;
            index--;
        }
        return curr.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > size || index < 0) return;
        ListNode curr = dummyHead;
        while (index > 0) {
            curr = curr.next;
            index--;
        }
        ListNode newNode = new ListNode(val);
        //curr指向index-1节点
        newNode.next = curr.next;
        curr.next = newNode;
        size++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index >= size || index < 0) return;
        ListNode curr = dummyHead;
        while (index > 0) {
            curr = curr.next;
            index--;
        }
        //curr指向index-1
        ListNode temp = curr.next;
        curr.next = temp.next;
        size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */