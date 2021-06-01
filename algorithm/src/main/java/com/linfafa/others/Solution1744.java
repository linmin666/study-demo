package com.linfafa.others;

/**
 * 题目：你能在你最喜欢的那天吃到你最喜欢的糖果吗？
 * 难度：中等🌟🌟
 * 题目描述：给你一个下标从0开始的正整数数组candiesCount，其中candiesCount[i]表示你拥有的第i类糖果的数目。
 * 同时给你一个二维数组queries，其中queries[i] = [favoriteTypei, favoriteDayi, dailyCapi]。
 * <p>
 * 你按照如下规则进行一场游戏：
 * 1.你从第0天开始吃糖果。
 * 2.你在吃完所有第i - 1类糖果之前，不能吃任何一颗第i类糖果。
 * 3.在吃完所有糖果之前，你必须每天至少吃一颗糖果。
 * <p>
 * 请你构建一个布尔型数组answer，满足answer.length == queries.length 。
 * answer[i]为true的条件是：在每天吃不超过dailyCapi颗糖果的前提下，
 * 你可以在第favoriteDayi天吃到第favoriteTypei类糖果；否则 answer[i]为false。
 * 注意，只要满足上面3条规则中的第二条规则，你就可以在同一天吃不同类型的糖果。
 * 请你返回得到的数组answer。
 * <p>
 * 解题思路：
 * 由题意，每天的吃糖数量在[1,queries[i][2]]区间内，因此我们可以计算出【最早/最晚】
 * 吃到queries[i][0]类糖果的时间，然后判断queries[i][1]是否落在该时间区间内。
 * 若是，answer[i]=true,否则answer[i]=false.
 * 问题转换为如何快速计算出【最早/最晚】吃到queries[i][0]类糖果的时间。（前缀和）
 * t = queries[i][0]    -- favoriteTypei,
 * d = queries[i][1]+1  -- favoriteDayi+1(因题中是从第0天开始，我们是从第一天开始)
 * c = queries[i][2]    -- dailyCapi
 * 最早时间（第一颗t类糖的最早时间）= 1+sum[t]/c    -- 以最大速率c吃掉糖果，吃掉第t类糖果前面的所有糖果的时间（下取整）+1
 * 最晚时间（最后一颗t类糖的最晚时间）= sum[t+1]    -- 以最小速率1吃掉糖果，吃掉所有t类糖果的时间
 *
 * @author linmin
 * @date 2021/6/1
 */
public class Solution1744 {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        boolean[] answers = new boolean[queries.length];
        long[] sum = new long[candiesCount.length + 1];//前缀和数组，下标从1开始
        for (int i = 1; i <= candiesCount.length; ++i)
            sum[i] = sum[i - 1] + candiesCount[i - 1];

        for (int i = 0; i < queries.length; ++i) {
            int t = queries[i][0], d = queries[i][1] + 1, c = queries[i][2];
            long minTime = 1 + sum[t] / c, maxTime = sum[t + 1];
            answers[i] = d >= minTime && d <= maxTime;
        }
        return answers;
    }

    public static void main(String[] args) {
        int[] candiesCount = {7, 4, 5, 3, 8};
        int[][] queries = {{0, 2, 2}, {4, 2, 4}, {2, 13, 100}};
        Solution1744 s = new Solution1744();
        boolean[] res = s.canEat(candiesCount, queries);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }

    }
}
