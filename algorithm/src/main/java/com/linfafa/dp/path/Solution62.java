package com.linfafa.dp.path;

/**
 * 题目：不同路径
 * 难度：中等
 * 题目描述：一个机器人位于一个m*n网格的左上角，机器人每次只能向下或向右移动一步。
 * 机器人需从左上角的起点到达右下角的终点，问总共有多少条不同的路径？
 * 提示：
 * （1）1<=m,n<=100
 * （2）题目数据保证答案小于等于2*10^9
 * <p>
 * 解题思路：本题可以使用动态规划来解，我们定义一个二维数组dp，dp[i][j]
 * 表示到达位置(i,j)的不同路径的数量。那么dp[m-1][n-1]的值就是我们要的答案，并且dp[0][0]=1;
 * 由于题目限定了我们只能「往上」或「往下」移动，因此，我们按「当前可选方向」进行分析：
 * （1）当前位置只能「往下」移动，那么dp[i][j] = dp[i-1][j];
 * （2）当前位置只能「往右」移动，那么dp[i][j] = dp[i][j-1];
 * （3）当前位置既能「往下」也能「往右」，那么dp[i][j] = dp[i-1][j] + dp[i][j-1];
 *
 * @author linmin
 * @date 2021/6/7
 */
public class Solution62 {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i > 0 && j > 0)
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                else if (i > 0)//i>0 && j=0
                    dp[i][j] = dp[i - 1][j];//只能往下
                else if (j > 0)//j>0 && i=0
                    dp[i][j] = dp[i][j - 1];//只能往右
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        Solution62 s = new Solution62();
        int res = s.uniquePaths(3, 3);
        System.out.println(res);
    }
}
