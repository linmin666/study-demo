package com.linfafa.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution56 {
    /**
     * 🌟🌟🌟🌟
     * 56.合并区间
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     * 分析题目:左区间取小，右区间取大
     * 区间交叠的情况
     * b左区间<=a左区间<=b右区间或a左区间<=b左区间<=a右区间，如[1,3],[2,6]或[1,2],[2,3]
     */

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[1][2];
        }
        List<int[]> merge = new ArrayList<int[]>();
        //给数组排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        int end = Integer.MIN_VALUE;
        int start = Integer.MAX_VALUE;
        for (int i = 0; i < intervals.length; i++) {
            int starti = intervals[i][0];//3
            int endi = intervals[i][1];//4
            //满足交集条件
            if ((starti <= end && starti >= start) || (start <= endi && start >= starti)) {
                merge.remove(merge.size() - 1);
                start = Math.min(starti, start);
                end = Math.max(endi, end);
                merge.add(new int[]{start, end});
            } else {
                //不交叠,直接加入结果数据
                start = starti;
                end = endi;
                merge.add(new int[]{starti, endi});
            }

        }

        return merge.toArray(new int[merge.size()][2]);
    }


    public static void main(String[] args) {
        Solution56 sort = new Solution56();
//        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] intervals = {{1, 2}, {3, 6}, {1, 10}};
        //{1,6},{8,10},{15,18}
        int[][] result = sort.merge(intervals);
        Arrays.stream(result).forEach(x -> System.out.println("[" + x[0] + "," + x[1] + "]"));

    }
}
