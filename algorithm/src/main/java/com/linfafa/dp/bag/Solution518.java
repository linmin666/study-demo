package com.linfafa.dp.bag;

/**
 * 题目：零钱兑换II
 * 难度：中等
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回0。
 * 假设每一种面额的硬币有无限个。
 * <p>
 * 题目数据保证结果符合32位带符号整数。
 * <p>
 * 解题思路：
 * dp[i][j]表示考虑前i个物品，总和不超过j的组合数。
 * 取0件i物品时，dp[i][j]=dp[i-1][j];
 * 取1件i物品时，dp[i][j]=dp[i-1][j-t]
 * ....
 * 取k件i物品时，dp[i][j]=dp[i-1][j-k*t],0<=k<=j/t
 * <p>
 * dp[i][j]=dp[i-1][j]+dp[i-1][j-t]+...+dp[i-1][j-k*t]
 *
 * @author linmin
 * @date 2021/6/11
 */
public class Solution518 {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= amount; ++j) {
                if (i == 0)
                    dp[0][j] = j % coins[0] == 0 ? 1 : 0;
                else {
                    for (int k = 0; k <= j / coins[i]; ++k) {
                        dp[i][j] += dp[i - 1][j - k * coins[i]];
                    }
                }
            }
        }
        return dp[n - 1][amount];
    }

    //一维
    public int change1(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; ++i) {
            int t = coins[i - 1];
            for (int j = t; j <= amount; ++j) {
                dp[j] += dp[j - t];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int amount = 5;
        int[] coins = {1, 2, 5};
        Solution518 s = new Solution518();
        int res = s.change1(amount, coins);
        System.out.println(res);
    }
}
