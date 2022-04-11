package com.linfafa.search.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：括号生成
 * 难度：中等
 * 描述：数字n代表生成的括号的对数。
 * 请你设计一个函数，用于能够生成所有可能的并且有效的括号组合。
 * <p>
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * 思路：
 * 1. 本题可以抽象为n个左括号和n个右括号放进长度为2n的数组中的方法数量,
 * 2. 任意一个右括号出现前，都必须先出现左括号
 * 因此：
 * 1. 一个合法的括号组合，最终得分为0；
 * 2. 在整个组合过程中，组合得分在[0,n]的范围内，最大得分不超过n；
 *
 * @author linmin
 * @date 2021/8/18
 */
public class Solution22 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(0, 2 * n, 0, n, "", res);
        return res;
    }

    /**
     * 1表示左括号得分，-1表示右括号的得分
     *
     * @param i     当前位置
     * @param n     字符总长度
     * @param score 当前得分
     * @param max   最大得分
     * @param path
     * @param res
     */
    void dfs(int i, int n, int score, int max, String path, List<String> res) {
        if (i == n) {
            if (score == 0)
                res.add(path);
        } else {

            if (score + 1 <= max) {//若当前分数+1不超过最大分数，就可以添加左括号
                dfs(i + 1, n, score + 1, max, path + "(", res);
            }

            if (score - 1 >= 0) {//若当前分数-1不小于0，就可以添加右括号
                dfs(i + 1, n, score - 1, max, path + ")", res);
            }
        }
    }

    public static void main(String[] args) {
        Solution22 s = new Solution22();
        List<String> res = s.generateParenthesis(3);
        System.out.println(res.size());
        res.forEach(System.out::println);

    }
}
