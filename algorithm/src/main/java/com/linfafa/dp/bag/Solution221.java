package com.linfafa.dp.bag;

/**
 * 题目：最大正方形
 * 难度：中等
 * 描述：在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 *
 * @author linmin
 * @date 2021/9/17
 */
public class Solution221 {
    /**
     * dp[i][j]表示以matrix[i][j]为右下角的最大正方形的边长
     * 那么dp[i][j]=min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])+1
     * 当i=0或j=0时，dp[i][j]=matrix[i][j]
     * 当matrix[i][j]=0时，dp[i][j]=0
     *
     * 时间复杂度：O(mn)
     * 空间复杂度：O(mn)
     */
    public int maximalSquare(char[][] matrix) {
        int maxLen = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxLen;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                if (matrix[i][j] == '0')
                    dp[i][j] = 0;
                else{
                 if(i==0 || j==0)
                     dp[i][j]=1;
                 else
                     dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
                maxLen = Math.max(dp[i][j], maxLen);
            }
        }
        return maxLen * maxLen;
    }

    public static void main(String[] args) {
        char[][] matrix= {
                {'1', '1', '1'},
                {'1','1','0'}
        };
        Solution221 s = new Solution221();
        int res = s.maximalSquare(matrix);
        System.out.println(res);

    }
}
