package com.linfafa.math;

/**
 * 题目：第N个泰波那契数
 * 难度：简单
 * 描述：泰波那契序列Tn定义如下：
 * T(0) = 0, T(1) = 1, T(2) = 1, 且在 n > 0 的条件下T(n+3) = T(n) + T(n+1) + T(n+2)
 * 给你整数n，请返回第n个泰波那契数T(n)的数值
 * 提示：0 <= n <= 37
 *
 * @author linmin
 * @date 2021/9/9
 */
public class Solution1137 {
    //动态规划
    //时间复杂度O(n),空间复杂度O(1)
    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        int a = 0, b = 1, c = 1;
        for (int i = 3; i <= n; i++) {
            int d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        return c;
    }

    /**
     * 矩阵快速幂
     * |f(n)  |   |1,1,1|   |f(n-1)|   |1,1,1| ^ n-2   |f(2)|
     * |f(n-1)| = |1,0,0| * |f(n-2)| = |1,0,0|       * |f(1)|
     * |f(n-2)|   |0,1,0|   |f(n-3)|   |0,1,0|         |f(0)|
     * 时间复杂度O(logN),空间复杂度O(1)
     */

    int N = 3;

    int[][] mul(int[][] a, int[][] b) {
        int[][] c = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j] + a[i][2] * b[2][j];
            }
        return c;
    }

    public int tribonacci1(int n) {
        if(n==0)return 0;
        if(n==1||n==2)return 1;
        int[][] ans=new int[][]{
                {1,0,0},
                {0,1,0},
                {0,0,1}
        };
        int[][] mat = new int[][]{
                {1,1,1},
                {1,0,0},
                {0,1,0}
        };
        int k=n-2;
        while (k!=0){
            if((k&1)!=0) ans=mul(ans,mat);
            mat=mul(mat,mat);
            k>>=1;
        }
        return ans[0][0]+ans[0][1];
    }

    public static void main(String[] args) {
        Solution1137 s = new Solution1137();
        int res = s.tribonacci1(25);
        System.out.println(res);
    }
}
