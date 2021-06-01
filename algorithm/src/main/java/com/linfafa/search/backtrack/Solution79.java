package com.linfafa.search.backtrack;

/**
 * 题目：单词搜索
 * 难度：中等🌟🌟🌟
 * 题目描述：给定一个m x n二维字符网格board和一个字符串单词word。
 * 如果word 存在于网格中，返回true；否则，返回false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻
 * 或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 *
 * @author linmin
 * @date 2021/5/12
 */
public class Solution79 {
    int[] direction = {-1, 0, 1, 0, -1};

    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) return false;
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                boolean flag = backtracking(board, word, 0, visited, i, j);
                if (flag) return true;
            }
        }
        return false;
    }

    private boolean backtracking(char[][] board, String word, int pos, boolean[][] visited, int i, int j) {
        if (board[i][j] != word.charAt(pos)) {//若已经访问过或当前网格不符合条件
            return false;
        }
        if (pos == word.length() - 1) {
            return true;
        }
        visited[i][j] = true;//修改当前访问状态
        boolean res = false;
        for (int k = 0; k < 4; ++k) {
            int x = i + direction[k], y = j + direction[k + 1];
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
                if(!visited[x][y]) {
                    boolean flag = backtracking(board, word, pos + 1, visited, x, y);
                    if (flag) {
                        res = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;//回改当前访问状态
        return res;
    }

    public static void main(String[] args) {
//        char[][] board={{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
//        String word="ABCB";
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        Solution79 s = new Solution79();
        boolean res = s.exist(board, word);
        System.out.println(res);
    }
}
