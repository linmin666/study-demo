package com.linfafa.search.binary;

import java.util.Arrays;

/**
 * 题目：在排序数组中查找元素的第一次和最后一次出现的位置
 * 难度：中等
 * 描述：给定一个按照升序排列的整数数组nums，和一个目标值target。
 * 找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值target，返回[-1, -1]。
 */
public class Solution34 {
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums == null) return res;
        int n = nums.length;
        int left = 0, right = n - 1;
        //找第一个
        while (left < right) {
            int mid = left + right >> 1;
            System.out.println("left="+left+",right="+right+",mid="+mid);
            if (nums[mid] >= target)
                right = mid;
            else
                left = mid + 1;
        }

        if (nums[left] != target) return res;
        res[0] = left;

        //找最后一个
        left = 0;
        right = n - 1;
        while (left < right) {
            System.out.println("left="+left+",right="+right);
            int mid = left + right + 1 >> 1;
            if (nums[mid] <= target) {
                left = mid;
            } else right = mid - 1;
        }

        res[1] = left;
        return res;

    }

    public static void main(String[] args) {
        int[] nums = {10, 10, 20, 30};
        int target = 10;
        Solution34 s = new Solution34();
        int[] res = s.searchRange(nums, target);
        Arrays.stream(res).forEach(System.out::print);
    }
}

