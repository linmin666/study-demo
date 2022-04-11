package com.linfafa.dp.path;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：最大得分路径数目
 * 难度：困难🌟🌟🌟🌟🌟
 * 题目描述：给你一个正方形字符数组board，你从数组最右下方的字符'S'出发。
 * 你的目标是到达数组最左上角的字符'E'，数组剩余的部分为数字字符1, 2, ..., 9或者障碍'X'。
 * 在每一步移动中，你可以向上、向左或者左上方移动，可以移动的前提是到达的格子没有障碍。
 * 一条路径的 「得分」 定义为：路径上所有数字的和。
 * 请你返回一个列表，包含两个整数：第一个整数是「得分」的最大值，第二个整数是得到最大得分
 * 的方案数，请把结果对10^9 + 7取余。
 * 如果没有任何路径可以到达终点，请返回[0, 0]。
 * <p>
 * 解题思路：
 * dp[i][j]=max(dp[i][j+1],dp[i+1][j],dp[i+1][j+1])+board[i][j]
 * 注：只有合法范围的格子才会参与转移，存在障碍物的格子的dp值为INF（负数）
 * 将dp降维，令idx=i*n+j
 * g[]表示方案数
 *
 * @author linmin
 * @date 2021/6/9
 */
public class Solution1301 {
    int INF = Integer.MIN_VALUE;
    int mod = (int) 1e9 + 7;

    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        char[][] chars = new char[n][n];
        for (int i = 0; i < n; ++i) {
            chars[i] = board.get(i).toCharArray();
        }

        int[] f = new int[n * n];//f(i)表示从右下角到i的最大得分
        int[] g = new int[n * n];//g(i)表示从右下角到i并取得最大分数的方案数
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                int idx = i * n + j;
                //初始化'S'格子
                if (i == n - 1 && j == n - 1) {
                    g[idx] = 1;
                    continue;
                }
                //障碍物
                if (chars[i][j] == 'X') {
                    f[idx] = INF;
                    continue;
                }
                //当前格子的分数
                int val = (i == 0 && j == 0) ? 0 : chars[i][j] - '0';
                //u表示最大得分，t表示最大得分方案数
                int u = INF, t = 0;

                //下方
                if (i + 1 < n) {
                    int cur = f[idx + n] + val;
                    int cnt = g[idx + n];
                    int[] res = update(cur, cnt, u, t);
                    u = res[0];
                    t = res[1];
                }
                //右方
                if (j + 1 < n) {
                    int cur = f[idx + 1]+val;
                    int cnt = g[idx + 1];
                    int[] res = update(cur, cnt, u, t);
                    u = res[0];
                    t = res[1];
                }
                //右下方
                if (i + 1 < n && j + 1 < n) {
                    int cur = f[idx + n + 1]+val;
                    int cnt = g[idx + n + 1];
                    int[] res = update(cur, cnt, u, t);
                    u = res[0];
                    t = res[1];
                }

                f[idx] = u < 0 ? INF : u;
                g[idx] = t;
            }
        }
        return new int[]{f[0] == INF ? 0 : f[0], g[0] == INF ? 0 : g[0]};
    }

    int[] update(int cur, int cnt, int u, int t) {
        int[] ans = new int[]{u, t};

        if (cur > u) {
            ans[0] = cur;
            ans[1] = cnt;
        }

        if (cur == u && cur != INF) {
            ans[1] += cnt;
        }
        ans[1] %= mod;
        return ans;
    }

    public static void main(String[] args) {
//        board = ["E23","2X2","12S"]
//        输出：[7,1]
        List<String> list=new ArrayList<>();
        list.add("E23");
        list.add("2X2");
        list.add("12S");

        Solution1301 s = new Solution1301();
        int[] res = s.pathsWithMaxScore(list);
        System.out.println("f="+res[0]+",g="+res[1]);
    }
}
