package com.linfafa.dp.bag;

/**
 * 题目：零钱兑换
 * 难度：中等
 * 题目描述：给定不同面额的硬币coins和一个总金额amount。编写一个函数来计算可以凑成
 * 总金额所需的最少硬币个数。如果没有任何一种硬币能组合成总金额，返回-1。
 * 你可以认为每种硬币的数量无限。
 *
 * @author linmin
 * @date 2021/6/10
 */
public class Solution322 {
    public int coinChange(int[] coins, int amount) {
        int INF = -1;
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        //考虑第一个硬币
        for (int j = 0; j <= amount; ++j) {
            int k = j / coins[0];
            if (k * coins[0] == j) dp[0][j] = k;
            else dp[0][j] = INF;
        }
        for (int i = 1; i < n; ++i) {
            int t = coins[i];
            for (int j = 0; j <= amount; ++j) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 0; k <= j / t; ++k) {
                    if (dp[i][j] == INF)
                        dp[i][j] = dp[i - 1][j - k * t] == INF ? INF : dp[i - 1][j - k * t] + k;
                    else if (dp[i - 1][j - k * t] != INF)
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k * t] + k);
                }
            }
        }
        return dp[n - 1][amount];
    }

    public int coinChange1(int[] coins, int amount) {
        int INF = -1;
        int n = coins.length;
        int[] dp = new int[amount + 1];
        //考虑第一个硬币
        for (int j = 0; j <= amount; ++j) {
            int k = j / coins[0];
            if (k * coins[0] == j) dp[j] = k;
            else dp[j] = INF;
        }
        for (int i = 1; i < n; ++i) {
            int t = coins[i];
            for (int j = 0; j <= amount; ++j) {
                for (int k = 0; k <= j / t; ++k) {
                    if (dp[j] == INF)
                        dp[j] = dp[j - k * t] == INF ? INF : dp[j - k * t] + k;
                    else if (dp[j - k * t] != INF)
                        dp[j] = Math.min(dp[j], dp[j - k * t] + k);
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {2, 5, 10, 1};
        int amount = 27;
        Solution322 s = new Solution322();
        int res = s.coinChange(coins, amount);
        System.out.println(res);
        //out 11
    }
}
