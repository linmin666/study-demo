package com.linfafa.others;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 题目：连续的子数组和
 * 难度：中等
 * 题目描述：给你一个整数数组nums和一个整数k，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
 * 子数组大小至少为2，且子数组元素总和为k的倍数。
 * 如果存在，返回true；否则，返回false。
 * 如果存在一个整数n，令整数x符合x = n * k，则称x是k的一个倍数。0始终视为k的一个倍数。
 * <p>
 * 解题思路：前缀和，预处理前缀和数组sum，若[i,j]是满足条件的区间，
 * 那么sum[j]-sum[i-1] = n*k;变形得sum[j]/k-sum[i-1]/k=n
 * 即sum[j]和sum[i-1]对k取余相同。
 *
 * @author linmin
 * @date 2021/6/4
 */
public class Solution523 {
    //时间复杂度O(n)
    public boolean checkSubarraySum(int[] nums, int k) {
        int[] sum = new int[nums.length + 1];

        for (int i = 1; i <= nums.length; ++i) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i <= nums.length; ++i) {
            set.add(sum[i - 2] % k);
            if (set.contains(sum[i] % k)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {23, 2, 4, 6, 7};
        int k = 6;
        Solution523 s = new Solution523();
        boolean res = s.checkSubarraySum(nums, k);
        System.out.println(res);
    }
}
