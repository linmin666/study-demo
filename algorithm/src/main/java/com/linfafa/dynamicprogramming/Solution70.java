package com.linfafa.dynamicprogramming;

/**
 * 题目：爬楼梯
 * 难度：简单
 * 题目描述：假设你正在爬楼梯。需要n阶你才能到达楼顶。
 * 每次你可以爬1或2个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定n是一个正整数。
 * <p>
 * 解题思路：定义一个数组dp，dp[i]表示走到第i个台阶的方法数，因为一次我们
 * 可以走一步或者两步，所以第i个阶梯是从第i-1一个阶梯或者第i-2个阶梯直接到达.
 * dp[i]=dp[i-1]+dp[i-2],dp[1]=1
 *
 * @author linmin
 * @date 2021/5/18
 */
public class Solution70 {
    public int climbStairs(int n) {//动态规划
        if (n == 1 || n == 0) return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Solution70 s = new Solution70();
        int res = s.climbStairs(1);
        System.out.println(res);
    }
}
