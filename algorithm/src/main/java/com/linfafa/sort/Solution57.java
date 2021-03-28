package com.linfafa.sort;

import java.util.Arrays;

public class Solution57 {
    /**
     * 给你一个无重叠的，按照区间起始端点排序的区间列表。
     * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
     * 解题思路：
     * 1、将加入区间左区间之外的区间加入结果集
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] res = new int[intervals.length + 1][2];
        int head = 0;
        int start = newInterval[0];
        int end = newInterval[1];
        int i = 0;
        while (i < intervals.length && intervals[i][1] < start) {//不重叠且小于左区间
            res[head++] = intervals[i++];
        }
        while (i < intervals.length && intervals[i][0] <= end) {//有重叠便进行合并，直到遍历到与右区间相离位置
            start = Math.min(start, intervals[i][0]);
            end = Math.max(intervals[i][1], end);
            i++;
        }
        res[head++] = new int[]{start, end};
        while (i < intervals.length) {//加入右区间以外数据
            res[head++] = intervals[i++];
        }
        return Arrays.copyOf(res, head);//注意res的长度是固定的，未填满的部分是[0,0],要去掉
    }


    public static void main(String[] args) {
        Solution57 sort = new Solution57();
//        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}};
//        int[][] intervals = {};//{{1, 3}, {6,9}};
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newIntervals = {10, 15};
//        {1,6},{8,10},{15,18}
        int[][] result = sort.insert(intervals, newIntervals);
        Arrays.stream(result).forEach(x -> System.out.println("[" + x[0] + "," + x[1] + "]"));

        //1，2
        //3，5
    }
}
