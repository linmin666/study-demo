package com.linfafa.dp.bag;

/**
 * 题目：多重背包的单调队列优化
 * 题目描述：有N种物品和一个容量为C的背包，每种物品「数量有限」。
 * 第i件物品的体积是v[i],价值是w[i],数量是s[i].
 * 问选择哪些物品，每件物品选择多少件，可使得总价值最大。
 * <p>
 * 解题思路：
 * 使用一维空间优化的定义：f[i]代表容量不超过i时的最大价值
 * 当遍历完所有的物品后，f[C]就是最优解。
 * 🌟🌟转移只发生在「对当前物品体积取取余相同」的状态之间🌟🌟
 * 举例：当前物品的体积为2，数量为3，背包的容量为10，
 * 那么存在如下规律：
 * f(10)只能由f(8)、f(6)、f(4)转移而来；
 * f(9)只能由f(7)、f(5)、f(3)转移而来；
 * ...
 * f(4)只能由f(2)、f(0)转移而来；
 * f(3)只能由f(1)转移而来；
 * f(2)只能由f(0)转移而来；
 * <p>
 * 综上，f(i)只能由s mod i相同(s为当前物品体积，i为当前背包容量)，并且比i小，数量不超过物品个数的状态值所更新。
 * 如果我们能在转移f[i]时，以O(1)或均摊O(1)的复杂度从「能够参与转移的状态」中找到最大值，我们就能省掉
 * 「朴素多重背包」解决方案中最内层的"决策"循环，从而将整体复杂度降低到O(N*C).
 * <p>
 * 具体实现：
 * 定义一个g数组，用于记录上一次物品的转移结果；定义一个q数组来充当队列，队列中存放本次转移的结果。
 * 由于我们希望在O(1)复杂度内取得「能够参与转移的状态」中的最大值，自然期望能够在对队列头部或者尾部
 * 直接取得目标值来更新f[i],这就需要维持「队列元素单调」和「特定的窗口大小」。
 *
 * @author linmin
 * @date 2021/6/21
 */
public class Solution05 {
    int maxValue(int[] v, int[] w, int[] s, int C, int N) {
        int[] dp = new int[C + 1];  //dp[i]表示容量不超过i时的最大价值
        int[] g = new int[C + 1];   //辅助队列，记录的时上一次的结果
        int[] q = new int[C + 1];   //主队列，记录本次的结果

        //枚举物品
        for (int i = 0; i < N; ++i) {
            int vi = v[i], wi = w[i], si = s[i];
            //将上次的结果存入辅助队列中
            g = dp.clone();

            //枚举余数
            for (int j = 0; j < vi; ++j) {
                //初始化队列，head和tail分别指向队列头部和尾部
                int head = 0, tail = -1;
                //枚举同一余数情况下，有多少种方案
                //如余数为1的情况下：1, vi+1, 2*vi+1, 3*vi+1...
                for (int k = j; k <= C; k += vi) {
                    dp[k] = g[k];
                    //将不在窗口范围内的值弹出
                    if (head <= tail && q[head] < k - si * vi) head++;
                    //如果队列中存在元素，直接使用队列头来更新
                    if (head <= tail) dp[k] = Math.max(dp[k], g[q[head]] + (k - q[head]) / vi * wi);

                    //TODO
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int N = 2, C = 5;
        int[] v = {1, 2}, w = {1, 2}, s = {2, 1};
        Solution05 solution05 = new Solution05();
        int res = solution05.maxValue(v, w, s, C, N);
        System.out.println(res);
    }
}
