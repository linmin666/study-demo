package com.linfafa.search.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：太平洋大西洋水流问题
 * 难度：中等🌟🌟🌟🌟
 * 题目描述：给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。
 * “太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
 * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
 * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
 * 提示：输出坐标的顺序不重要，m 和 n 都小于150
 *
 * @author linmin
 * @date 2021/5/8
 */
public class Solution417 {
    int[] direction = {-1, 0, 1, 0, -1};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights.length == 0 || heights[0].length == 0) return null;
        List<List<Integer>> res = new ArrayList<>();
        boolean[][] can_reach_a = new boolean[heights.length][heights[0].length];
        boolean[][] can_reach_p = new boolean[heights.length][heights[0].length];

        for (int i = 0; i < heights.length; ++i) {
            dfs(heights, i, 0, can_reach_p);
            dfs(heights, i, heights[0].length - 1, can_reach_a);
        }

        for (int j = 0; j < heights[0].length; ++j) {
            dfs(heights, 0, j, can_reach_p);
            dfs(heights, heights.length - 1, j, can_reach_a);
        }

        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if (can_reach_a[i][j] && can_reach_p[i][j]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    res.add(list);
                }
            }
        }
        return res;
    }

    void dfs(int[][] heights, int i, int j, boolean[][] can_reach) {
        if (can_reach[i][j]) return;
        can_reach[i][j] = true;
        int x, y;
        for (int k = 0; k < 4; ++k) {
            x = i + direction[k];
            y = j + direction[k + 1];
            if (x >= 0 && x < heights.length && y >= 0 && y < heights[0].length && heights[x][y] >= heights[i][j]) {
                dfs(heights, x, y, can_reach);
            }
        }
    }

    public static void main(String[] args) {
        int[][] nums = {{1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}};
        /*
                ~  ~   ~   ~   ~   ~  ~
                ~  1   2   2   3  (5) *
                ~  3   2   3  (4) (4) *
                ~  2   4  (5)  3   1  *
                ~ (6) (7)  1   4   5  *
                ~ (5)  1   1   2   4  *
                *  *   *   *   *   *  *
         */

        Solution417 s = new Solution417();
        List<List<Integer>> lists = s.pacificAtlantic(nums);
        lists.forEach(list -> {
            int x = list.get(0);
            int y = list.get(1);
            System.out.println("x="+x+",y="+y);
            System.out.println(nums[x][y]);
        });

    }
}
