package com.linfafa.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * 注意:
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 */
public class Solution435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length==0)return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {//将区间按结尾升序排序，若结尾相同，按开头升序
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] - o2[1] == 0) return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });
        int res = 0, prev = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            //if(intervals[i][0]<intervals[i-1][1])
            // 注意这种写法是错误的，因为这样前一位会包含被排除的区间
            if (intervals[i][0] < prev) {//有交叉，需要去掉一个区间
                res++;
            } else prev = intervals[i][1];
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{0,2},{1,3},{2,4},{3,5},{4,6}};
        Solution435 s = new Solution435();
        int res = s.eraseOverlapIntervals(intervals);
        System.out.println(res);
    }
}
