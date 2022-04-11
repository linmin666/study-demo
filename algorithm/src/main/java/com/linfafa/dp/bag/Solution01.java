package com.linfafa.dp.bag;

/**
 * 题目：01背包
 * 题目描述：有N件物品和一个容量是V的背包。每件物品有且只有一件。
 * 第i件物品的体积是v[i],价值是w[i].
 * 求解将哪些物品装入背包，可使物品的总体积不超过背包总量，且总价值最大。
 *
 * @author linmin
 * @date 2021/6/9
 */
public class Solution01 {
    /**
     * dp[N][C+1]
     * dfs(int[] v,int[] w,int i,int c) --i表示当前枚举的物品，c表示剩余容量
     * dp[i][c]=max(dp[i-1][c],dp[i-1][c-v[i]]+w[i])
     * 时间复杂度O(N*C),空间复杂度O(N*C)
     */
    public int maxValue(int N, int C, int[] v, int[] w) {
        int[][] dp = new int[N][C + 1];

        //初始化，考虑第一件物品的情况
        for (int i = 1; i <= C; ++i) {
            dp[0][i] = i >= v[0] ? w[0] : 0;
        }

        for (int i = 1; i < N; ++i) {
            for (int j = 1; j <= C; ++j) {
                //不选当前物品
                int m = dp[i - 1][j];
                //选当前物品
                int n = j >= v[i] ? dp[i - 1][j - v[i]] + w[i] : 0;
                dp[i][j] = Math.max(m, n);
            }
        }
        return dp[N - 1][C];
    }

    /**
     * 空间优化 dp[2][C+1]
     * 时间复杂度O(N*C)，空间复杂度O(C)
     */
    public int maxValue1(int N, int C, int[] v, int[] w) {
        int[][] dp = new int[2][C + 1];

        //初始化，考虑第一件物品的情况
        for (int i = 1; i <= C; ++i) {
            dp[0][i] = i >= v[0] ? w[0] : 0;
        }

        for (int i = 1; i < N; ++i) {
            for (int j = 1; j <= C; ++j) {
                //不选当前物品
                int m = dp[(i - 1) & 1][j];
                //选当前物品
                int n = j >= v[i] ? dp[(i - 1) & 1][j - v[i]] + w[i] : 0;
                dp[i & 1][j] = Math.max(m, n);
            }
        }
        return dp[(N - 1) & 1][C];
    }

    /**
     * dp[C+1]
     * 时间复杂度O(N*C)，空间复杂度O(C)
     */
    public int maxValue2(int N, int C, int[] v, int[] w) {
        int[] dp = new int[C + 1];
        for (int i = 1; i < N; ++i) {
            for (int j = C; j >= 1; --j) {
                //不选当前物品
                int m = dp[j];
                //选当前物品
                int n = j >= v[i] ? dp[j - v[i]] + w[i] : 0;
                dp[j] = Math.max(m, n);
            }
        }
        return dp[C];
    }

    public static void main(String[] args) {
        Solution01 s = new Solution01();
        int N = 3, C = 5;
        int[] v = {4, 2, 3}, w = {4, 2, 3};
        int res = s.maxValue2(N, C, v, w);
        System.out.println(res);
    }
}
