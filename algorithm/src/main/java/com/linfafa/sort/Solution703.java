package com.linfafa.sort;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 题目：数据流中的第K大元素
 * 难度：简单🌟🌟🌟
 * 描述：设计一个找到数据流中第K大元素的类。注意是排序后的第K大元素，
 * 不是第K个不同的元素。
 * 请实现 KthLargest类：
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 * <p>
 * 输入：
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * 输出：
 * [null, 4, 5, 5, 8, 8]
 * <p>
 * 提示：
 * 1 <= k <= 10^4
 * 0 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * -104 <= val <= 104
 * 最多调用 add 方法 104 次
 * 题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
 *
 * @author linmin
 * @date 2021/8/16
 */
public class Solution703 {

    public static void main(String[] args) {
        int[] nums = {4, 5, 8, 2};
        int k = 3;
        KthLargest2 kthLargest = new KthLargest2(k, nums);
        int add1 = kthLargest.add(3);
        System.out.println("add1 = " + add1);
        int add2 = kthLargest.add(5);
        System.out.println("add2 = " + add2);
        int add3 = kthLargest.add(10);
        System.out.println("add3 = " + add3);
        int add4 = kthLargest.add(9);
        System.out.println("add4 = " + add4);
        int add5 = kthLargest.add(4);
        System.out.println("add5 = " + add5);

    }
}

/**
 * 冒泡排序解法
 * 通过取k次最大值计算TopK
 * 时间复杂度：O(nk)
 * 空间复杂度：O(n)
 */
class KthLargest {
    int k;
    List<Integer> list = new ArrayList<>(10009);

    public KthLargest(int k, int[] list) {
        this.k = k;
        for (int num : list) this.list.add(num);
    }

    public int add(int val) {
        list.add(val);
        int len = list.size();
        int cur = 0; //使用冒泡排序，将最大值放在前面
        //find top k max value
        for (int i = 0; i < k; i++) {
            int maxIdx = findMax(cur, len - 1);
            swap(cur++, maxIdx);
        }
        return list.get(cur - 1);
    }

    int findMax(int start, int end) {
        int idx = 0, max = Integer.MIN_VALUE;
        int i;
        for (i = start; i <= end; i++) {
            if (list.get(i) > max) {
                max = list.get(i);
                idx = i;
            }
        }
        return idx;
    }

    void swap(int i, int j) {
        Integer tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}

/**
 * 快排解法
 * 使用冒泡排序的时间复杂度为O(nk)，当k很大时，可能会导致超时。
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(n)
 */
class KthLargest1 {
    int k;
    List<Integer> list = new ArrayList<>(10009);

    public KthLargest1(int k, int[] nums) {
        this.k = k;
        for (int num : nums) list.add(num);
    }

    public int add(int val) {
        list.add(val);
        Collections.sort(list);
        return list.get(list.size() - k);
    }
}

/**
 * 优先队列解法
 * 解题思路：使用优先队列构建一个容量为k的小根堆，
 * 将nums的前k项放入优先队列中（此时堆顶元素为前k项的最大值），
 * 随后逐项加入优先队列：
 * * 堆内元素达到k个：
 * * 加入项小于等于堆顶元素：加入项在第k大元素的后面，忽略；
 * * 加入项大于堆顶元素：将堆顶元素弹出，加入项加入优先队列，堆调整；
 * * 堆内元素小于k个：将加入项加入优先队列；
 *
 * 时间复杂度：O(nlogk)
 * 空间复杂度：O(k)
 */
class KthLargest2 {
    int k;
    PriorityQueue<Integer> queue;

    public KthLargest2(int k, int[] nums) {
        this.k = k;
        queue = new PriorityQueue<>(k);
        int n = nums.length;
        for (int i = 0; i < k && i < n; i++) queue.add(nums[i]);
        for (int i = k; i < n; i++) add(nums[i]);
    }

    public int add(int val) {
        int top = !queue.isEmpty() ? queue.peek() : Integer.MIN_VALUE;
        //加入项大于堆顶元素或堆内元素小于k个
        if (val > top || queue.size() < k) {
            //堆内元素达到k个，且加入项大于堆顶元素，弹出堆顶元素
            if (!queue.isEmpty() && queue.size() >= k)
                queue.poll();
            queue.add(val);
        }
        return queue.peek();
    }
}