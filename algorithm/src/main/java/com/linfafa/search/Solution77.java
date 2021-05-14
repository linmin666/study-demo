package com.linfafa.search;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 题目：组合
 * 难度：中等
 * 题目描述：给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 输入: n = 4, k = 2
 * 输出:
 * [[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]
 * <p>
 * 解题思路：类似于46题的排列，排列回溯的是交换位置，组合回溯的是把当前数字加入到结果中。
 *
 * @author linmin
 * @date 2021/5/11
 */
public class Solution77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) return res;
        Deque<Integer> comb = new LinkedList<>();
        backtracking(k, n, 1, comb, res);
        return res;
    }

    private void backtracking(int k, int n, int level, Deque<Integer> comb, List<List<Integer>> res) {
        //终止条件
        if (comb.size() == k) {
            res.add(new ArrayList<>(comb));
            return;
        }
        for (int i = level; i <= n; ++i) {
            comb.addLast(i);
            backtracking(k, n, i + 1, comb, res);
            comb.removeLast();
        }
    }

    public static void main(String[] args) {
        Solution77 s = new Solution77();
        List<List<Integer>> res = s.combine(4, 2);
        res.forEach(list -> {
            System.out.println("[" + list.get(0) + "," + list.get(1) + "]");
        });

    }

}
