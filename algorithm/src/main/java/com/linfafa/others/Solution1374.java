package com.linfafa.others;

/**
 * @author linmin
 * @date 2022/8/1
 * 题目：生成每种字符都是奇数个的字符串
 * 难度：简单
 * 题目描述：给你一个整数 n，请你返回一个含 n 个字符的字符串，其中每种字符在该字符串中都恰好出现 奇数次 。
 * 返回的字符串必须只含小写英文字母。如果存在多个满足题目要求的字符串，则返回其中任意一个即可。
 * n = 4
 * 输出："pppz"
 * 解释："pppz"
 */
public class Solution1374 {
    public String generateTheString(int n) {
        char a = 'a';
        char b = 'b';
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
            sb = sb.append(a);
        }
        if (n % 2 == 0)
            sb.append(b);
        else sb.append(a);
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution1374 s = new Solution1374();
        System.out.println(s.generateTheString(2));
    }
}