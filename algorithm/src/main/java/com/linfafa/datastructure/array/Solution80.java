package com.linfafa.datastructure.array;

import java.util.Arrays;

/**
 * 给你一个有序数组 nums ，请你原地删除重复出现的元素，使每个元素最多出现两次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 解题思路：举例1，1，1，2，2，3，首先直接保留1，1，然后判断nums[2]与nums[2-2]的关系，如相等，则为重复，如不想等则符合条件
 */
public class Solution80 {
    public int removeDuplicates(int[] nums) {
        int n = 0;
        for (int num : nums) {
            if (n < 2 || num != nums[n - 2])//当n<2或者当前位置的值!=当前位置减2位置的值时，执行赋值操作，否则读取下一个
                nums[n++]=num;
        }
        return n;
    }


    public static void main(String[] args) {
        Solution80 s = new Solution80();
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int res = s.removeDuplicates(nums);
        System.out.println(res);
        Arrays.stream(nums).forEach(System.out::print);
    }
}
