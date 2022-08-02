package com.linfafa.datastructure.queue;

/**
 * 题目：设计循环队列
 * 难度：中等
 * 题目描述：设计你的循环队列实现。循环队列是一种线性数据结构，其操作表现基于FIFI原则并且队尾
 * 被连接在队首之后以形成一个循环。它也被称为"环形缓冲器"。
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，
 * 我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 * <p>
 * * @author linmin
 *
 * @date 2022/8/2
 */
public class MyCircularQueue {

    private int[] array;
    //指向队列头部
    private int head;
    //指向队列尾部下一个位置（待插入元素）
    private int tail;
    //注意：tail、head始终递增🌟🌟

    //队列容量
    private int size;

    /**
     * 构造器，设置队列长度为k
     *
     * @param k
     */
    public MyCircularQueue(int k) {
        array = new int[k];
        size = k;
        head = tail = 0;
    }

    /**
     * 向循环队列插入一个元素。如果成功插入返回真
     *
     * @param value
     * @return
     */
    public boolean enQueue(int value) {
        //若队列还未满，则添加元素，否则返回false
        if (isFull()) return false;
        array[tail++ % size] = value; //在队尾插入原属,tail后移
        return true;
    }

    /**
     * 从循环队列中删除一个元素。如果成功删除则返回真。
     * [注意]：删除元素要遵循先进先出的原则
     *
     * @return
     */
    public boolean deQueue() {
        if (isEmpty()) return false;
        head++; // head后移
        return true;
    }

    /**
     * 从队首获取元素。如果队列为空，返回-1
     *
     * @return
     */
    public int Front() {
        if (isEmpty()) return -1;
        return array[head % size];

    }

    /**
     * 获取队尾元素。如果队列为空，返回-1。
     *
     * @return
     */
    public int Rear() {
        if (isEmpty()) return -1;
        return array[(tail - 1) % size];
    }

    /**
     * 检查循环队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return tail == head ? true : false;
    }

    public boolean isFull() {
        return tail - head == size ? true : false;
    }

    public static void main(String[] args) {
        MyCircularQueue queue = new MyCircularQueue(3);

        System.out.println("enQueue: " + queue.enQueue(1));
        System.out.println("enQueue: " + queue.enQueue(2));
        System.out.println("enQueue: " + queue.enQueue(3));


        System.out.println("Rear: " + queue.Rear());

        System.out.println("deQueue: " + queue.deQueue());
        System.out.println("Rear: " + queue.Rear());
    }


}
