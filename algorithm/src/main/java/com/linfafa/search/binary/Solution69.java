package com.linfafa.search.binary;

/**
 * 题目：x的平方根
 * 难度：简单
 * 题目描述：实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * @author linmin
 * @date 2021/5/28
 */
public class Solution69 {
    public int mySqrt(int x) {
        int left = 0, right = x;
        int mid = 0;
        while (left <= right) {//[left,right]
            mid = left + (right - left) / 2;
            long power = (long) mid * mid; //注意整型溢出
            if (power > x) right = mid - 1;//[left,mid-1]
            else if (power < x) left = mid + 1;//[mid+1,right]
            else return mid;
        }
        return mid * mid > x ? mid - 1 : mid;
    }

    public static void main(String[] args) {
        int x = 2147483647;
        Solution69 s = new Solution69();
        int res = s.mySqrt(x);
        System.out.println(res);
    }
}
