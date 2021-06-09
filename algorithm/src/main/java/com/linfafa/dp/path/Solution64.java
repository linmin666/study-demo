package com.linfafa.dp.path;

/**
 * 题目：最小路径和
 * 难度：中等
 * 题目描述：给定一个包含非负整数的m x n网格grid，请找出一条
 * 从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 解题思路：
 * dp[i][j]=min(dp[i][j-1],dp[i-1][j])+grid[i][j],i>0 && j>0
 * dp[i][j]=dp[i-1][j]+grid[i][j],i>0 && j=0
 * dp[i][j]=dp[i][j-1]+grid[i][j],i=0 && j>0
 * dp[0][0]=grid[0][0]
 *
 * @author linmin
 * @date 2021/6/8
 */
public class Solution64 {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i > 0 && j > 0)
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
                else if (i > 0)
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                else if (j > 0)
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
//       int[][] grid = {{1,3,1},{1,5,1}, {4,2,1}};
        int[][] grid = {{1, 2, 3}, {4, 5, 6}};
        Solution64 s = new Solution64();
        int res = s.minPathSum(grid);
        System.out.println(res);

    }

}
