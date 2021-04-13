package com.linfafa.doublepointer;

import scala.util.parsing.combinator.testing.Str;

import java.util.HashMap;
import java.util.Map;

/**
 * 🌟🌟🌟🌟🌟
 * 76. 最小覆盖子串
 * 给你一个字符串s、一个字符串t。返回s中涵盖t所有字符的最小子串。
 * 如果s中不存在涵盖t所有字符的子串，则返回空字符串""。
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 */
public class Solution76 {
    public String minWindow(String s, String t) {
        if (s == null || s.isEmpty() || t == null || t.isEmpty()) return "";
        int[] need = new int[128];
        int[] have = new int[128];
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }
        int left = 0, right = 0;//滑动窗口的两个指针
        int start = 0, min = Integer.MAX_VALUE;//记录结果子串的起始位置和最小长度
        int count = 0;//计数器，计算滑动窗口内需要字符的个数

        while (right < s.length()) {
            //1.延伸窗口
            char rChar = s.charAt(right);
            //1.1 t中无right指向的字符,继续向右滑动
            if (need[rChar] == 0) {
                right++;
                continue;
            }
            //1.2 t中有right指向的字符，分两种情况
            //a. 滑动窗口中该字符的个数小于t中该字符的个数,计数器+1
            if (have[rChar] < need[rChar]) {
                count++;
            }
            have[rChar]++;
            right++;

            //2. 判断当前窗口是否为可行窗口
            while (count == t.length()) {//可行窗口
                if (right - left < min) {
                    min = right - left;
                    start = left;
                }
                //3. 收缩窗口
                char lChar = s.charAt(left);
                //3.1 t中不包含left指向的字符
                if (need[lChar] == 0) {
                    left++;
                    continue;
                }
                //3.2 t中包含left指向的字符，类似right的处理方法
                if (have[lChar] == need[lChar]) {
                    count--;
                }
                have[lChar]--;
                left++;
            }
        }
        //4.判断结果
        if (min == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(start, start + min);
    }

    public static void main(String[] args) {
        String s = "cabwefgewcwaefgcf";
        String t = "cae";
        Solution76 s76 = new Solution76();
        String res = s76.minWindow(s, t);
        System.out.println(res);

    }
}
