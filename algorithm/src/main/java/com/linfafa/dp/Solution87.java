package com.linfafa.dp;

/**
 * 使用下面描述的算法可以扰乱字符串s得到字符串 t ：
 * 如果字符串的长度为 1 ，算法停止
 * 如果字符串的长度 > 1 ，执行下述步骤：
 * 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串s，则可以
 * 将其分成两个子字符串x和y，且满足 s = x + y 。
 * 随机决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。
 * 即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
 * 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
 * 给你两个长度相等的字符串s1和s2，判断s2是否是s1的扰乱字符串。如果是，返回true;否则，返回false。
 *
 */
public class Solution87 {
    public boolean isScramble(String s1, String s2) {
        if(s1.length()==1)return false;

        return false;
    }
}
