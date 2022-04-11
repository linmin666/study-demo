package com.linfafa.dp.path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目：三角形最小路径和
 * 难度：中等
 * 题目描述：给定三角形triangle，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的节点上。相邻的节点在这里指的是
 * 下标与上一层节点下标相同或者上一层节点下标+1的两个节点。也就是说，
 * 如果正位于当前行的下标i，那么下一步可以移动到下一行的下标i或i+1。
 * <p>
 * 解题思路：
 * dp[i][j]=min(dp[i-1][j],dp[i-1][j-1])+grid[i][j],i>0 && j>0
 * dp[i][j]=dp[i-1][j]+grid[i][j],j=0
 * dp[0][0]=grid[0][0]
 *
 * @author linmin
 * @date 2021/6/8
 */
public class Solution120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (i > 0 && j == i)//右侧边界
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                else if (i > 0 && j == 0)//左侧边界
                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                else if (i > 0 && j > 0)
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
            }
        }
        int min = dp[n - 1][0];
        for (int i = 0; i < n; ++i) {
            min = Math.min(min, dp[n - 1][i]);
        }
        return min;
    }

    /**
     * 空间优化：（1）滚动数组；（2）根据状态依赖调整迭代/循环方向
     * 滚动数组：只需要将数组的一维改成2，任何将维的dp[i]改成dp[i & 1]或dp[i % 2]即可
     */
    public int minimumTotal1(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[2][n];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                int val = triangle.get(i).get(j);
                if (i > 0 && j == i)//右侧边界
                    dp[i & 1][j] = dp[(i - 1) & 1][j - 1] + val;
                else if (i > 0 && j == 0)//左侧边界
                    dp[i & 1][j] = dp[(i - 1) & 1][j] + val;
                else if (i > 0 && j > 0)
                    dp[i & 1][j] = Math.min(dp[(i - 1) & 1][j], dp[(i - 1) & 1][j - 1]) + val;
            }
        }
        int min = dp[(n - 1) & 1][0];
        for (int i = 0; i < n; ++i) {
            min = Math.min(min, dp[(n - 1) & 1][i]);
        }
        return min;
    }

    public static void main(String[] args) {

        int[][] a = {{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}};
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        l1.add(2);
        triangle.add(l1);
        List<Integer> l2 = new ArrayList<>();
        l2.add(3);
        l2.add(4);
        triangle.add(l2);
        Solution120 s = new Solution120();
        int res = s.minimumTotal(triangle);
        System.out.println(res);
    }
}
