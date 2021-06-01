package com.linfafa.search.dfs;

/**
 * 题目：省份数量
 * 难度：中等🌟🌟🌟
 * 题目描述：
 * 有n个城市，其中一些彼此相连，另一些没有相连。如果城市a与城市b直接相连，
 * 且城市b与城市c直接相连，那么城市a与城市 c 间接相连。
 * 省份是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个n x n的矩阵isConnected ，其中isConnected[i][j] = 1 表示第i个城市
 * 和第j个城市直接相连，而isConnected[i][j] = 0表示二者不直接相连。
 * <p>
 * 返回矩阵中省份的数量。
 * isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * <p>
 * 解题思路：
 * 本题和695题相似，本题的节点数为n个，一个节点最多可以有n条边，最少可以有一条边（自己）。
 */
public class Solution547 {

    public int findCircleNum(int[][] isConnected) {
        if(isConnected.length==0)return 0;
        boolean[] visited=new boolean[isConnected.length];
        int count=0;
        for(int i=0;i< visited.length;++i){
            if(!visited[i]){
                ++count;
                dfs(isConnected,i,visited);
            }
        }
        return count;
    }

    void dfs(int[][] isConnected,int i,boolean[] visited){
        visited[i]=true;
        for(int j=0;j< visited.length;++j){
            if(isConnected[i][j]==1 && !visited[j]){
                dfs(isConnected,j,visited);
            }
        }
    }

    public static void main(String[] args) {
        int[][] isConnected = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        Solution547 s = new Solution547();
        int circleNum = s.findCircleNum(isConnected);
        System.out.println(circleNum);
    }
}
