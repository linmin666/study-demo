package com.linfafa.dp.path;

/**
 * 题目：出界的路径数
 * 难度：中等🌟🌟🌟
 * 题目描述：给定一个 m × n 的网格和一个球。球的起始坐标为(i,j)，你可以将球移到相邻的单元格内，
 * 或者往上、下、左、右四个方向上移动使球穿过网格边界。但是，你最多可以移动N次。
 * 找出可以将球移出边界的路径数量。答案可能非常大，返回结果 mod 10^9+7 的值。
 *
 * @author linmin
 * @date 2021/6/9
 */
public class Solution576 {
    int mod = 1000000007;
    int m, n, maxMove;

    /**
     * dfs(int m,int n,int i,int j,int move)
     * m,n是不变参，i，j表示当前位置，move表示剩余的移动次数；
     * 由于当前有三个可变参，我们可以考虑将i，j降为一维，令index=i*n+j，
     * 可得(x,y)=(index / n,index % n)
     * 令dp[index][move]表示从(index / n,index % n)可以用不超过move的步数到达边界的路径数量，
     * index的取值范围[0,m*n),move的取值范围[0,N]
     */
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        this.maxMove = maxMove;
        this.n = n;
        this.m = m;
        int[][] dp = new int[m * n][maxMove + 1];
        //初始化边缘格子的路径数量
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0) add(i, j, dp);
                if (j == 0) add(i, j, dp);
                if (i == m - 1) add(i, j, dp);
                if (j == n - 1) add(i, j, dp);
            }
        }

        //定义四个方向
        int[] direction = {-1, 0, 1, 0, -1};
        //从小到大枚举可移动步数
        for (int move = 1; move <= maxMove; ++move) {
            //枚举所有位置
            for (int k = 0; k < m * n; ++k) {
                int i = k / n, j = k % n;
                for (int d = 0; d < 4; ++d) {
                    //新位置
                    int x = i + direction[d], y = j + direction[d + 1];
                    //如果位置有相邻格子，则相邻格子参与状态转移
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        dp[k][move] += dp[x * n + y][move - 1];
                        dp[k][move] %= mod;
                    }
                }
            }
        }
        return dp[startRow * n + startColumn][maxMove];
    }

    void add(int i, int j, int[][] dp) {
        for (int move = 1; move <= maxMove; ++move) {
            dp[i * n + j][move]++;
        }
    }

    public static void main(String[] args) {
//        输入: m = 2, n = 2, N = 2, i = 0, j = 0
//        输出: 6
        int m = 2, n = 2, N = 2, i = 0, j = 0;
        Solution576 s = new Solution576();
        int res = s.findPaths(m, n, N, i, j);
        System.out.println(res);
    }
}
