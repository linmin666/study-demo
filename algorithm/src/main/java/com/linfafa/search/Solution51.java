package com.linfafa.search;

import java.util.*;

/**
 * 题目：N皇后
 * 难度：困难🌟🌟🌟🌟🌟
 * 题目描述：n皇后问题研究的是如何将n个皇后放置在n×n的棋盘上，并且使皇后
 * 彼此之间不能相互攻击。给你一个整数n，返回所有不同的n皇后问题的解决方案。
 * 每一种解法包含一个不同的n皇后问题的棋子放置方案，该方案中'Q'和'.'分别代表了皇后和空位。
 * 提示：
 * (1)1 <= n <= 9;
 * (2)皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 * <p>
 * 解题思路：
 * 使用一个数组记录每行放置的皇后的列下标，依次在每一行放置一个皇后。每次新放置的皇后
 * 都不能和已经放置的皇后之间有攻击：即新放置的皇后不能和任何一个已经放置的皇后在同一列
 * 以及同一条斜线上，并更新数组中的当前行的皇后列下标。当N个皇后都放置完毕，
 * 则找到一个可能的解。当找到一个可能的解之后，将数组转换成表示棋盘状态的列表，
 * 并将该棋盘状态的列表加入返回列表。
 * 由于每个皇后必须位于不同列，因此已经放置的皇后所在的列不能放置别的皇后。
 * 第一个皇后有N列可以选择，第二个皇后最多有N−1列可以选择，第三个皇后最多有N−2列可以选择
 * （如果考虑到不能在同一条斜线上，可能的选择数量更少），因此所有可能的情况不会超过N!种，
 * 遍历这些情况的时间复杂度是O(N!)。
 * 为了降低总时间复杂度，每次放置皇后时需要快速判断每个位置是否可以放置皇后，显然，
 * 最理想的情况是在 O(1)的时间内判断该位置所在的列和两条斜线上是否已经有皇后。
 * <p>
 *
 * @author linmin
 * @date 2021/5/14
 */
public class Solution51 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        //记录每一列上是否有皇后
        Set<Integer> column = new HashSet<>();
        //记录\这个方向的斜线上是否有皇后（行下标减列下标相等的格子处于同一斜线）
        Set<Integer> diagonal1 = new HashSet<>();
        //记录/这个方向的斜线上是否有皇后（行下标加列下标相等的格子处于同一斜线）
        Set<Integer> diagonal2 = new HashSet<>();
        //用来记录结果，下标表示行，value表示列
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        backtracking(n, 0, queens, column, diagonal1, diagonal2, res);
        return res;
    }

    private void backtracking(int n, int row, int[] queens, Set<Integer> column, Set<Integer> diagonal1, Set<Integer> diagonal2, List<List<String>> res) {
        if (row == n) {
            res.add(generateBoard(queens));
        } else {
            for (int i = 0; i < n; i++) {

                int d1 = row - i;
                int d2 = row + i;
                if (column.contains(i)
                        || diagonal1.contains(d1)
                        || diagonal2.contains(d2))
                    continue;

                queens[row] = i;
                column.add(i);
                diagonal1.add(d1);
                diagonal2.add(d2);
                backtracking(n, row + 1, queens, column, diagonal1, diagonal2, res);

                //回溯,恢复状态
                queens[row] = -1;
                column.remove(i);
                diagonal1.remove(d1);
                diagonal2.remove(d2);

            }
        }
    }

    private List<String> generateBoard(int[] queens) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < queens.length; ++i) {
            char[] chars = new char[queens.length];
            Arrays.fill(chars, '.');
            int index = queens[i];
            chars[index] = 'Q';
            board.add(new String(chars));
        }
        return board;
    }

    public static void main(String[] args) {
        Solution51 s = new Solution51();
        List<List<String>> lists = s.solveNQueens(9);
       for (List<String> list : lists) {
           System.out.println("结果：");
           for (String s1 : list) {
               System.out.println(s1);
           }
        }
    }
}
