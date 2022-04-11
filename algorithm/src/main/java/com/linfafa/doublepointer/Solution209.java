package com.linfafa.doublepointer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 题目：长度最小的子数组
 * 难度：中等
 * 题目描述：给定一个含n个正整数的数组和一个正整数target。
 * 找出该数组中满足其和≥target的长度最小的连续子数组[numsl, numsl+1, ..., numsr-1, numsr]，
 * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <p>
 * 解题思路：滑动窗口
 *
 * @author linmin
 * @date 2021/5/31
 */
public class Solution209 {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0;//滑动窗口指针
        int min = Integer.MAX_VALUE; //子数组初始位置和最小长度
        int sum = 0;
        while (right < nums.length) {
            sum += nums[right++];
            while (sum >= target && left <= right) {
                min = Math.min(right - left, min);
                System.out.println("start="+left+",sum="+sum);
                sum -= nums[left++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public int minSubArrayLen(String a){return 0;}
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int target = 10;
        Solution209 s = new Solution209();
        int len = s.minSubArrayLen(target, nums);
        System.out.println(len);
    }
}
