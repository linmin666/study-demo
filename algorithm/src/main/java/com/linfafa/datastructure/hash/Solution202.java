package com.linfafa.datastructure.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 题目：快乐树
 * 难度：简单
 * 描述：编写一个算法来判断一个数 n 是不是快乐数。
 * <p>
 * 「快乐数」定义为：
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果 可以变为1，那么这个数就是快乐数。
 * 如果 n 是快乐数就返回 true ；不是，则返回 false 。
 *
 * @author linmin
 * @date 2021/5/31
 */
public class Solution202 {
    public boolean isHappy(int n) {
        Set<Integer> sumSet = new HashSet<>();
        while (true) {
            int sum = getSum(n);
            if (sum == 1) return true;
            if (sumSet.contains(sum)) return false;
            else sumSet.add(sum);
            n=sum;
        }
    }

    int getSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution202 s = new Solution202();
        boolean res = s.isHappy(2);
        System.out.println(res);
    }
}
