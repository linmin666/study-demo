package com.linfafa.doublepointer;

/**
 * 680.验证回文子串II
 * 给定一个非空字符串s，最多删除一个字符。判断是否能成为回文字符串。
 */
public class Solution680 {
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return valid(s, left, right - 1) || valid(s, left + 1, right);
            }
            left++;
            right--;
        }
        return true;
    }

    public boolean valid(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "";
        Solution680 s = new Solution680();
        boolean res = s.validPalindrome(str);
        System.out.println(res);
    }
}
