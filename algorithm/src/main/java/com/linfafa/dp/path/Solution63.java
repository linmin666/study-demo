package com.linfafa.dp.path;

/**
 * 题目：不同路径II
 * 难度：中等
 * 题目描述：一个机器人位于m*n网格的左上角，机器人每次只能向下或向右移动一步。
 * 机器人试图达到网格的右下角，现在考虑网格中有障碍物。那么从左上角到右下角有多少不同的路径？
 * <p>
 * 解题思路：本题和62题几乎一样，唯一不同便是grid[i][j]=1的位置，dp[i][j]=0
 *
 * @author linmin
 * @date 2021/6/7
 */
public class Solution63 {
    public int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0] == 1 ? 0 : 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] != 1) {
                    if (i > 0 && j > 0)
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    else if (i > 0)
                        dp[i][j] = dp[i - 1][j];
                    else if (j > 0)
                        dp[i][j] = dp[i][j - 1];

                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] grid={{0,0,0},{0,1,0},{0,0,0}};
        Solution63 s = new Solution63();
        int res = s.uniquePathsWithObstacles(grid);
        System.out.println(res);
    }
}
