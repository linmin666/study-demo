package com.linfafa.dp;

/**
 * 题目：最大子序和
 * 难度：简单
 * 描述：给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 思路：dp[i]表述i下标往前的最大子序列和
 * dp[i]=max(dp[n-1]+a[n],a[n])
 * i=0时，dp[i]=a[0];
 *
 * @author linmin
 * @date 2021/9/22
 */
public class Solution53 {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        int maxSum=Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            if(i==0) dp[0] = nums[0];
            else dp[i]=Math.max(dp[i -1]+nums[i],nums[i]);
            maxSum=Math.max(maxSum,dp[i]);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums={-2,1,-3,4,-1,2,1,-5,4};
        Solution53 s = new Solution53();
        int res = s.maxSubArray(nums);
        System.out.println(res);
    }
}
