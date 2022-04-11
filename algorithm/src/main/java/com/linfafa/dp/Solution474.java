package com.linfafa.dp;

/**
 * 题目：一和零
 * 难度：中等
 * 题目描述：给你一个二进制字符串数组strs和两个整数m和n。
 * 请你找出并返回strs的最大子集的大小，该子集中最多有m个0和n个1 。
 * 如果x的所有元素也是y的元素，集合x是集合y的子集。
 * <p>
 * 注：参考宫水三叶的题解
 *
 * @author linmin
 * @date 2021/6/6
 */
public class Solution474 {
    /**
     * 注：参考宫水三叶的题解
     * 解题思路：01背包问题（多维）
     * 要将原题转换为「背包问题」，往往需要从题目中抽象出「价值」和「成本」的概念。
     * 这道题抽象成「背包问题」的话，应该是：
     *
     * 每个字符串的价值都是1（对答案的贡献都是1），选择的成本是该字符串中1和0的数量。
     * 问我们在1数量不超过m，0的数量不超过n，最大价值是多少？
     *
     *
     */
    public int findMaxForm(String[] strs, int m, int n) {

        return 0;
    }
}