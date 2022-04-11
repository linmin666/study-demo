package com.linfafa.dp.bag;

/**
 * 题目：完全背包
 * 题目描述：有N中物品和一个容量为C的背包，每件物品都有无限件。
 * 第i件物品的体积是v[i],价值是w[i].
 * 求解将哪些物品装入背包可使这些物品的费用总和不超过背包总容量，且价值总和最大。
 *
 * @author linmin
 * @date 2021/6/10
 */
public class Solution02 {
    /**
     * 常规解法：
     * dp[i][j]表示考虑前i个物品，放入容量为j的背包中可以获得的最大价值
     * 由于每件物品可以选择多次，因此对于某个dp[i][j]而言，其值应该为以下所有可能方案中的最大值：
     * （1）选择0件物品i的最大价值，即dp[i-1][j]
     * （2）选择1件物品i的最大价值，即dp[i-1][j-v[i]]+w[i]
     * （3）选择2件物品i的最大价值，即dp[i-1][j-2v[i]]+2w[i]
     * ....
     * （k）选择k件物品i的最大价值，即dp[i-1][j-kv[i]]+kw[i]
     * 状态转移方程：dp[i][j]=max(dp[i-1][j],dp[i-1][j-k*v[i]]+k*w[i]),0<k*v[i]<=j
     */
    public int maxValue(int N, int C, int[] v, int[] w) {
        int[][] dp = new int[N][C + 1];
        for (int i = 1; i <= C; ++i) {
            dp[0][i] = i >= v[0] ? w[0] : 0;
        }

        for (int i = 1; i < N; ++i) {
            for (int j = 1; j <= C; ++j) {
                int no = dp[i - 1][j];
                int yes = 0;
                int t = w[i];
                for (int k = 1; ; ++k) {
                    if (k * v[i] > j) break;
                    yes = Math.max(yes, dp[i - 1][j - k * v[i]] + k * w[i]);
                }
                dp[i][j] = Math.max(no, yes);
            }
        }
        return dp[N - 1][C];
    }

    //滚动数组
    public int maxValue1(int N, int C, int[] v, int[] w) {
        int[][] dp = new int[2][C + 1];
        for (int i = 1; i <= C; ++i) {
            dp[0][i] = i >= v[0] ? w[0] : 0;
        }

        for (int i = 1; i < N; ++i) {
            for (int j = 1; j <= C; ++j) {
                int no = dp[(i - 1) & 1][j];
                int yes = 0;
                int t = w[i];
                for (int k = 1; ; ++k) {
                    if (k * v[i] > j) break;
                    yes = Math.max(yes, dp[(i - 1) & 1][j - k * v[i]] + k * w[i]);
                }
                dp[i & 1][j] = Math.max(no, yes);
            }
        }
        return dp[(N - 1) & 1][C];
    }

    //一维数组🌟🌟
    public int maxValue2(int N, int C, int[] v, int[] w) {
        int[] dp = new int[C + 1];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j <= C; ++j) {
                int no = dp[j];
                int yes = j >= v[i] ? dp[j - v[i]] + w[i] : 0;
                dp[j] = Math.max(no, yes);

            }
        }
        return dp[C];
    }

    public static void main(String[] args) {
        int N = 2, C = 5;
        int[] v = {1, 2}, w = {1, 2};
        Solution02 s = new Solution02();
        int res = s.maxValue2(N, C, v, w);
        System.out.println(res);
    }
}
