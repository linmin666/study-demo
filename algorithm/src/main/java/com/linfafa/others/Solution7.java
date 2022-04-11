package com.linfafa.others;

/**
 * 题目：整数反转
 * 难度：简单
 * 描述：给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [-2^ 31,2^31-1]。
 * 请根据这个假设，如果反转后整数溢出那么就返回 0。
 * @author linmin
 * @date 2021/8/18
 */
public class Solution7 {

    public int reverse(int num) {
        long ans = 0; //避免溢出
        while (num != 0) {
            ans = ans * 10 + num % 10;
            num = num / 10;
        }
        return (int) ans == ans ? (int) ans : 0;
    }


}
