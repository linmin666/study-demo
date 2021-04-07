package com.linfafa.dynamicprogramming;

/**
 * 给定两个字符串text1 和text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * 解题思路：
 * 这是一道典型的动态规划题。使用dp[i][j]表示s1[0:i]和s2[0:j]的最长共同子序列长度。
 * 当s1[i]==s2[j]时，dp[i][j]=dp[i-1][j-1]+1;
 * 当s1[i]!=s2[j]时， 要考虑两种情况，dp[i-1][j]和dp[i][j-1],两者取大
 */
public class Solution1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] state = new int[text1.length()+1][text2.length()+1];
        for (int i = 0; i <= text1.length(); i++) {
            for (int j = 0; j <= text2.length(); j++) {
                if (i == 0 || j == 0) state[i][j] = 0; //考虑边界情况
                else if (text1.charAt(i-1) == text2.charAt(j-1)) {//注意i-1和j-1是index的减1
                    state[i][j] = state[i - 1][j - 1] + 1;
                } else {
                    state[i][j] = Math.max(state[i - 1][j], state[i][j - 1]);
                }

            }
        }
        return state[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        String text1 = "abc", text2 = "def";
        Solution1143 s = new Solution1143();
        int res = s.longestCommonSubsequence(text1, text2);
        System.out.println(res);
    }
}
