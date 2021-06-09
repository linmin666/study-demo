package com.linfafa.dp.path;

/**
 * 题目：下降路径最小和
 * 难度：中等
 * 题目描述：给你一个n x n的方形整数数组matrix，请你找出并返回通过matrix的下降路径的最小和。
 * 下降路径可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行
 * 所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。
 * 具体来说，位置(row, col)的下一个元素应当是(row + 1, col - 1)、(row + 1, col)
 * 或者 (row + 1, col + 1) 。
 * <p>
 * 解题思路：
 * dp[i][j]=min(dp[i-1][j],dp[i-1][j+1])+matrix[i][j]; j=0,i>0
 * dp[i][j]=min(dp[i-1][j-1],dp[i-1][j])+matrix[i][j]; j=n-1,i>0
 * dp[i][j]=min(dp[i-1][j-1],dp[i-1][j],dp[i-1][j+1])+matrix[i][j];
 *
 * @author linmin
 * @date 2021/6/8
 */
public class Solution931 {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i) {//第一行
            dp[0][i] = matrix[0][i];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (j == 0)//左侧边界
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j + 1]) + matrix[i][j];
                else if (j == n - 1)//右侧边界
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + matrix[i][j];
                else
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j + 1])) + matrix[i][j];
            }
        }

        int min = dp[n - 1][0];
        for (int i = 1; i < n; ++i) {
            min = Math.min(min, dp[n - 1][i]);
        }
        return min;
    }

    public static void main(String[] args) {
//        int[][] matrix = {{2,1,3},{6,5,4},{7,8,9}};
        int[][] matrix = {{-19,57},{-40,-5}};
        Solution931 s = new Solution931();
        int res = s.minFallingPathSum(matrix);
        System.out.println(res);
    }
}
