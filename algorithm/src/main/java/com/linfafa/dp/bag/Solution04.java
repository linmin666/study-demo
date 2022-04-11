package com.linfafa.dp.bag;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：多重背包的二进制优化
 * 题目描述：有N种物品和一个容量为C的背包，每种物品「数量有限」。
 * 第i件物品的体积是v[i],价值是w[i],数量是s[i].
 * 问选择哪些物品，每件物品选择多少件，可使得总价值最大。
 *
 * 解题思路：我们目前学的多重背包在各维度数量级同阶的情况下，时间复杂度是O(n^3)，只能解决10^2数量级的问题。
 * 二进制优化可以使我们能解决的多重背包问题数量级从10^2上升为10^3.
 * 我们之前的扁平化方式是直接展开，这样并没有减少运算量，但如果我们能将10变成小于10个数，那这样的扁平化是有意义的。
 * 我们的思路是：将原本为n的物品用ceil(log(n))个数来代替，从而降低算法复杂度。
 * (对「物品」做分类，使总数量为m的物品能够用更小的logm个数所组合表示出来)
 *
 * @author linmin
 * @date 2021/6/21
 */
public class Solution04 {
    /**
     * 时间复杂度： sum(log(s[i]))*C，0<=i<=n-1
     * 空间复杂度：sum(log(s[i]))+C,0<=i<=n-1
     */
    int maxValue(int[] v, int[] w, int[] s, int C, int N) {
        //扁平化
        List<Integer> worth = new ArrayList<>();
        List<Integer> volume = new ArrayList<>();

        //将每件物品都进行扁平化，首先遍历所有的物品
        for (int i = 0; i < N; i++) {
            //获取每件物品的出现次数
            int val = s[i];
            //进行扁平化：如果一件物品规定的使用次数为7次，
            // 我们将其扁平化为3件物品：1*重量&1*价值、2*重量&2*价值、4*重量&4*价值
            //三件物品都不选对应了我们使用该物品0次的情况，
            //只选第一件扁平物品对应使用该物品1次的情况，
            //只选择第二件扁平物品对应使用该物品2次的情况，
            //只选择第一件和第二件扁平物品对应使用该物品3次的情况
            //...
            for (int k = 1; k <= val; k *= 2) {
                val -= k;
                worth.add(w[i] * k);
                volume.add(v[i] * k);
            }
            if (val > 0) {
                worth.add(w[i] * val);
                volume.add(v[i] * val);
            }
        }

        int[] dp = new int[C + 1];
        for (int i = 0; i < worth.size(); ++i) {
            for (int j = C; j >= volume.get(i); --j) {
                dp[j] = Math.max(dp[j], dp[j - volume.get(i)] + worth.get(i));
            }
        }
        return dp[C];
    }

    public static void main(String[] args) {
        int N = 2, C = 5;
        int[] v = {1, 2}, w = {1, 2}, s = {2, 1};
        Solution04 solution04 = new Solution04();
        int res = solution04.maxValue(v, w, s, C, N);
        System.out.println(res);
    }
}
