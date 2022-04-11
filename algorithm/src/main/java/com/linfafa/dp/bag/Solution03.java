package com.linfafa.dp.bag;

/**
 * 题目：多重背包
 * 题目描述：有N种物品和一个容量为C的背包，每种物品「数量有限」。
 * 第i件物品的体积是v[i],价值是w[i],数量是s[i].
 * 问选择哪些物品，每件物品选择多少件，可使得总价值最大。
 * 状态转移方程：
 * dp[i][j]=max(dp[i-1][j],dp[i-1][j-k*w[i]]+k*w[i]),0<=k<=s[i] && 0<=k*w[i]<=j
 * dp[i][j]表示考虑前i个物品，在容量为j的背包中的最大价值
 *
 * @author linmin
 * @date 2021/6/21
 */
public class Solution03 {
    int maxValue(int[] v, int[] w, int[] s, int C, int N) {
        int[][] dp = new int[N][C + 1];
        for (int j = 1; j <= C; j++) {
            if (j / w[0] <= s[0]) {
                dp[0][j] = j / w[0] * w[0];
            } else dp[0][j] = w[0] * s[0];
        }
        for (int i = 1; i < N; ++i) {
            for (int j = 1; j <= C; ++j) {
                for (int k = 0; k <= s[i] && k * w[i] <= j; ++k) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * v[i]] + k * w[i]);
                }
            }
        }
        return dp[N - 1][C];
    }

    //滚动数组
    int maxValue1(int[] v, int[] w, int[] s, int C, int N) {
        int[][] dp = new int[2][C + 1];
        for (int j = 1; j <= C; j++) {
            if (j / w[0] <= s[0]) {
                dp[0][j] = j / w[0] * w[0];
            } else dp[0][j] = w[0] * s[0];
        }
        for (int i = 1; i < N; ++i) {
            for (int j = 1; j <= C; ++j) {
                for (int k = 0; k <= s[i] && k * w[i] <= j; ++k) {
                    dp[i & 1][j] = Math.max(dp[i & 1][j], dp[(i - 1) & 1][j - k * w[i]] + k * w[i]);
                }
            }
        }
        return dp[(N - 1) & 1][C];
    }

    public static void main(String[] args) {
        int N = 2, C = 5;
        int[] v = {1, 2}, w = {1, 2}, s = {2, 1};
        Solution03 solution03 = new Solution03();
        int res = solution03.maxValue(v, w, s, C, N);
        System.out.println(res);
    }
}
