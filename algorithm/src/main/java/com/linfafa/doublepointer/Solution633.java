package com.linfafa.doublepointer;

/**
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 */
public class Solution633 {
    public boolean judgeSquareSum(int c) {
        int h = 0;
        int t = (int) Math.sqrt(c);
        int sum = 0;
        while (h <= t) {
            sum = h * h + t * t;
            if (sum == c) {
                return true;
            } else if (sum > c) {
                t--;
            } else {
                h++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int c = 1000000;
        Solution633 s = new Solution633();
        boolean res = s.judgeSquareSum(c);
        System.out.println(res);
    }
}
