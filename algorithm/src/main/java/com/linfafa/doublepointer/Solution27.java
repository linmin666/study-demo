package com.linfafa.doublepointer;

import java.util.Arrays;

/**
 * 题目描述：给你一个数组nums和一个值val你需要原地移除所有数值等于val的元素并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用O(1)额外空间并原地修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * @author linmin
 * @date 2021/5/28
 */
public class Solution27 {

    public int removeElement(int[] nums, int val) {
        //排序后使用双指针
        Arrays.sort(nums);
        int prev = 0;
        int cur = 0;
        while (cur < nums.length) {
            if (nums[cur] == val) {
                prev = cur;
                break;
            }
            cur++;
        }
        cur = nums.length - 1;
        while (cur >= 0) {
            if (nums[cur] == val) break;
            cur--;
        }
        cur++;//往后移动一位
        int n = prev + nums.length - cur;
        while (prev < n) {
            nums[prev] = nums[cur];
            cur++;
            prev++;
        }
        return n;
    }

    public int removeElement2(int[] nums, int val) {
        //不需要排序，直接使用双指针
        int cur = 0, tail = nums.length - 1;
        while (cur <= tail) {
            if (nums[cur] == val) {
                swap(nums, cur, tail);
                tail--;
                cur--;//检查交换后的cur是否还是val🌟🌟🌟
            }
            cur++;
        }
        return tail + 1;
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Solution27 s = new Solution27();
        int[] nums = {2, 2, 3, 2};
        int target = 2;
//        int res = s.removeElement(nums, target);
//        System.out.println(res);
//        Arrays.stream(nums).forEach(System.out::print);

        int res2 = s.removeElement2(nums, target);
        System.out.println(res2);
        Arrays.stream(nums).forEach(System.out::print);
    }
}
