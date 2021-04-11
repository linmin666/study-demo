package com.linfafa.others;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 给你一个整数n，请你找出并返回第n个丑数 。1690>=n>=1
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数
 */
public class Solution264 {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[p2] * 2, Math.min(dp[p3] * 3, dp[p5] * 5));
            if (dp[i] == 2 * dp[p2]) p2++;
            if (dp[i] == 3 * dp[p3]) p3++;
            if (dp[i] == 5 * dp[p5]) p5++;
            //注：这里的判断条件不能写else if，因为dp[i]可能同时满足2*dp[p2]和3*dp[p3]等；
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Solution264 s = new Solution264();
        int res = s.nthUglyNumber(0);
        System.out.println(res);
    }
}
