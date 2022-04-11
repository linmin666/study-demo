package com.linfafa.dp.bag;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：完全平方数
 * 难度：中等
 * 题目描述：给定正整数n，找到若干个完全平方数（比如1，4，9，16...）使得它们的和等于n。
 * 你需要让组成和的完全平方数的个数最少。
 * 给你一个整数n，返回和为n的完全平方数的最少数量。
 * 完全平方数是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
 * <p>
 * 解题思路：
 * dp[i][j]表示考虑前i件物品时，和等于j的最小数量
 * 选0件i物品，dp[i][j]=dp[i-1][j]
 * 选1件i物品，dp[i][j]=dp[i-1][j-t]+1
 * 选2件i物品，dp[i][j]=dp[i-1][j-2t]+2
 * 选k件i物品，dp[i][j]=dp[i-1][j-kt]+k
 * 转移状态方程：dp[i][j]=min(dp[i-1][j-kt]+k),0<=kt<=j
 *
 * @author linmin
 * @date 2021/6/10
 */
public class Solution279 {
    public int numSquares(int n) {
        int INF = -1;
        //构建物品,由于不确定数组长度，我们可以使用集合
        List<Integer> list = new ArrayList<>();
        //初始化集合
        int idx = 1;
        while (idx * idx <= n) {
            list.add(idx * idx);
            idx++;
        }
        int len = list.size();
        //dp[i][j]表示考虑前i个数，满足和等于n的最小数量
        int[][] dp = new int[len][n + 1];
        //处理第1件物品
        for (int j = 1; j <= n; ++j) {
            int t = list.get(0);
            int k = j / t;
            if (k * t == j)
                dp[0][j] = k;
            else
                dp[0][j] = -1;
        }

        for (int i = 1; i < len; ++i) {
            int t = list.get(i);
            for (int j = 1; j <= n; ++j) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 0; k <= j / t; ++k) {
                    if (dp[i - 1][j - k * t] != INF)
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k * t] + k);
                }
            }
        }

        return dp[len - 1][n];
    }
    public int numSquares1(int n) {
        int INF = -1;
        //构建物品,由于不确定数组长度，我们可以使用集合
        List<Integer> list = new ArrayList<>();
        //初始化集合
        int idx = 1;
        while (idx * idx <= n) {
            list.add(idx * idx);
            idx++;
        }
        int len = list.size();
        //dp[i][j]表示考虑前i个数，满足和等于n的最小数量
        int[] dp = new int[n + 1];
        //处理第1件物品
        for (int j = 1; j <= n; ++j) {
            int t = list.get(0);
            int k = j / t;
            if (k * t == j)
                dp[j] = k;
            else
                dp[j] = -1;
        }

        for (int i = 1; i < len; ++i) {
            int t = list.get(i);
            for (int j = 1; j <= n; ++j) {
                dp[j] = dp[j];
                for (int k = 0; k <= j / t; ++k) {
                    if (dp[j - k * t] != INF)
                        dp[j] = Math.min(dp[j], dp[j - k * t] + k);
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Solution279 s = new Solution279();
        int res = s.numSquares(13);
        System.out.println(res);
    }
}
