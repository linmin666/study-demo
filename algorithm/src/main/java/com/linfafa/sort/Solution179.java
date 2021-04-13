package com.linfafa.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * 输入：nums = [10,2]
 * 输出："210"
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 * 解题思路：类似于字典序
 */
public class Solution179 {
    public String largestNumber(int[] nums) {
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = Integer.toString(nums[i]);
        }
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });

        String res = Arrays.stream(strings).reduce((s1, s2) -> s1 + s2).get();
        if (res.charAt(0) == '0')
            return "0";
        return res;

    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1};
        Solution179 s = new Solution179();
        String res = s.largestNumber(nums);
        System.out.println(res);
    }
}
