package com.linfafa.search;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 题目：岛屿的最大面积
 * 难度：中等
 * 题目描述：给定一个包含了一些0和1的非空二维数组grid。
 * 一个岛屿是由一些相邻的1(代表土地) 构成的组合，这里的相邻要求两个1必须在水平
 * 或者竖直方向上相邻。你可以假设grid 的四个边缘都被0代表水）包围着。
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0)
 * 示例 1:
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回6。注意答案不应该是11 ，因为岛屿只能包含水平或垂直的四个方向的1。
 * <p>
 * 解题思路：
 * 深度优先搜索，这种题可以分为主函数和辅函数，主函数用于遍历所有位置，判断是否开始搜索，如果可以，
 * 便使用辅函数搜索。辅函数则负责深度优先搜索的递归调用，也可以使用栈来实现递归调用。
 */
public class Solution695 {
    //每相邻的两位组合就是上下左右四个方向之一
    int[] direction = {-1, 0, 1, 0, -1};

    /**
     * 解法二：深度优先搜索+栈
     */
    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length==0 ||grid[0].length==0)return 0;
        int res=0;
        for(int i=0;i<grid.length;++i){
            for(int j=0;j<grid[0].length;++j){
                if(grid[i][j]==1){
                    int area=1;
                    grid[i][j]=0;
                    Deque<Integer> stack_i=new LinkedList<>();
                    Deque<Integer> stack_j=new LinkedList<>();
                    stack_i.add(i);
                    stack_j.add(j);
                    while (!stack_i.isEmpty()){
                        int cur_i=stack_i.pop();
                        int cur_j=stack_j.pop();
                        int x,y;
                        for(int k=0;k<4;++k){
                            x=cur_i+direction[k];
                            y=cur_j+direction[k+1];
                            if(x>=0 && x< grid.length && y>=0 && y<grid[0].length && grid[x][y]==1){
                                grid[x][y]=0;
                                ++area;
                                stack_i.add(x);
                                stack_j.add(y);
                            }
                        }
                    }
                    res=Math.max(res,area);
                }
            }
        }
        return res;
    }
        /**
         * 解法一：深度优先搜索+递归
         * 时间复杂度
         */
    public int maxAreaOfIsland1(int[][] grid) {//主函数
        if (grid.length == 0 || grid[0].length == 0) return 0;

        int res =0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(grid, i, j));
                }
            }
        }
        return res;
    }


    private int dfs(int[][] grid, int cur_i, int cur_j) {//辅函数
        if (grid[cur_i][cur_j] == 0) return 0;
        grid[cur_i][cur_j] = 0;
        int x, y, aera = 1;//当前的这个需要计数
        //遍历四个方向
        for (int i = 0; i < 4; i++) {
            x = cur_i + direction[i];
            y = cur_j + direction[i + 1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
                aera += dfs(grid, x, y);
            }
        }
        return aera;
    }

    public static void main(String[] args) {
        int[][] grids={};
        int[][] nums = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        Solution695 s = new Solution695();
        int res = s.maxAreaOfIsland(grids);
        System.out.println(res);
    }
}
