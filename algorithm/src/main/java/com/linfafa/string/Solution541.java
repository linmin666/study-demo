package com.linfafa.string;

/**
 * 题目：反转字符串II
 * 难度：简单
 * 题目描述：给定一个字符串s和一个整数k，你需要对从字符串开头算起的每隔2k个字符的前k个字符进行反转。
 * <p>
 * 如果剩余字符少于k个，则将剩余字符全部反转。
 * 如果剩余字符小于2k但大于或等于k个，则反转前k个字符，其余字符保持原样。
 * <p>
 * 输入: s = "abcdefg", k = 2
 * 输出: "bacdfeg"
 *
 * @author linmin
 * @date 2021/6/1
 */
public class Solution541 {
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        for (int i = 0; i < n; i += 2 * k) {
            if (i + k <= n) {
                reverse(chars, i, i + k - 1);
                continue;
            }
            reverse(chars, i, n - 1);
        }
        return String.valueOf(chars);
    }
   //左闭右闭
    void reverse(char[] chars, int start, int end) {
        int offset = (end - start + 1) / 2;
        for (int i = start, j = end; i < start + offset; i++, j--) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
    }

    //左闭右开
//    void reverse(char[] chars, int i, int j) {
//
//        int n = i + (j - i) / 2;
//        for (int k = i; k <i + (j - i) / 2; ++k) {
//            char temp = chars[k];
//            chars[k] = chars[n - k + i];
//            chars[n - k + i] = temp;
//        }
//    }


    public static void main(String[] args) {
        String str = "abcdef";
        Solution541 s = new Solution541();
        char[] chars = str.toCharArray();
//        s.reverse(chars, 0,2);
//        s.reverse(chars, 0, 2);
//        System.out.println(String.valueOf(chars));
        String s1 = s.reverseStr(str, 3);
        System.out.println(s1);
    }
}
