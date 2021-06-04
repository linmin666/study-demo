package com.linfafa.datastructure.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 题目：连续数组
 * 难度：中等
 * 题目描述：给定一个二进制数组nums, 找到含有相同数量的0和1的最长连续子数组，并返回该子数组的长度。
 * 解题思路：由于nums是二进制数组，且需要包含相同数量的0和1，所以子数组长度(长度至少为2)必然为偶数
 * 且在预处理前缀和数组时，我们将0作为-1处理，若[i,j]为符合条件的区间，
 * 可得 sum[j]-sum[i-1] = 0
 *
 * @author linmin
 * @date 2021/6/4
 */
public class Solution525 {
    //前缀和+哈希表
    public int findMaxLength(int[] nums) {
        int[] sum = new int[nums.length + 1];
        int maxLen = 0;
        for (int i = 1; i <= nums.length; ++i) {
            sum[i] = sum[i - 1] + (nums[i - 1] == 0 ? -1 : 1);
        }
        //key表示前缀和，value表示下标
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 2; i <= nums.length; ++i) {
            if (!map.containsKey(sum[i - 2])) map.put(sum[i - 2], i - 2);
            if (map.containsKey(sum[i])) maxLen = Math.max(maxLen, i - map.get(sum[i]));
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Solution525 s = new Solution525();
        int[] nums = {0, 1, 0, 1};
        int maxLength = s.findMaxLength(nums);
        System.out.println(maxLength);
    }
}
