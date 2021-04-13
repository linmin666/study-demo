package com.linfafa.search;

import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组nums，和一个目标值target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值target，返回[-1, -1]。
 */
public class Solution34 {
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums.length == 0) return res;

        int left = 0, right = nums.length - 1;
        //开始位置
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else left = mid + 1;
        }
        if (nums[left] != target) return res;

        res[0] = left;
        left = 0;
        right = nums.length - 1;
        //结束位置
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (nums[mid] <= target) {
                left = mid;
            } else right = mid - 1;
        }
        res[1] = right;
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {10};
        int target = 10;
        Solution34 s = new Solution34();
        int[] res = s.searchRange(nums, target);
        Arrays.stream(res).forEach(System.out::print);
    }
}

