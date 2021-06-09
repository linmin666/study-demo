package com.linfafa.dp.path;

/**
 * 题目：下降路径最小和II
 * 难度：困难
 * 题目描述：给你一个整数方阵arr，定义「非零偏移下降路径」为：
 * 从arr数组中的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。
 * 请你返回非零偏移下降路径数字和的最小值。
 * <p>
 * 解题思路：
 * dp[i][j]=min(dp[i-1][!j])+arr[i][j]
 *
 * @author linmin
 * @date 2021/6/8
 */
public class Solution1289 {
    public int minFallingPathSum(int[][] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i) dp[0][i] = arr[0][i];//第一行

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                dp[i][j] = min(dp[i - 1], j) + arr[i][j];
            }
        }
        int min = dp[n - 1][0];
        for (int i = 0; i < n; ++i) min = Math.min(min, dp[n - 1][i]);
        return min;
    }

    int min(int[] row, int index) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < row.length; ++i) {
            if (i != index) {
                min = Math.min(min, row[i]);
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Solution1289 s = new Solution1289();
        int res = s.minFallingPathSum(arr);
        System.out.println(res);
    }
}
