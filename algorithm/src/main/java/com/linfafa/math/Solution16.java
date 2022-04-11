package com.linfafa.math;

import java.util.Arrays;

/**
 * 题目：最接近的三数之和
 * 难度：中等
 * 描述：给定一个包括n个整数的数组nums和一个目标值target。找出nums中的三个整数，使得它们的和与target最接近。
 * 返回这三个数的和。假定每组输入只存在一个唯一的答案。
 * 示例：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 时间复杂度：排序的复杂度是O(logN)，i是枚举的，复杂度是O(n),i确定后，j和k对于i而言复杂度是O(n)，
 * 最终时间复杂度为O(n^2)
 * 空间复杂度：O(n^2)
 * @author linmin
 * @date 2021/7/13
 */
public class Solution16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < n; ++i) {
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (Math.abs(sum - target) < Math.abs(res - target)) res = sum;
                if (sum == target)
                    return sum;
                else if (sum < target) j++;
                else k--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
       int[] nums = {-1,2,1,-4} ;
       int target = 1;
        Solution16 s = new Solution16();
        int res = s.threeSumClosest(nums, target);
        System.out.println(res);
    }
}
