package com.linfafa.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目：四数之和
 * 难度：中等
 * 描述：给定一个包含n个整数的数组nums和一个目标值target，判断nums中是否存在四个元素a，b，c，d，
 * 使得a+b+c+d的值等于target？找出所有满足条件且不重复的四元组。
 * 示例：
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 时间复杂度：i和j是直接枚举的，复杂度是O(n^2)，当i和j确定后，通过双指针确定k和p，
 * 对于每组i和j而言复杂度是O(n)，所以最终复杂度是O(n^3)
 * 空间复杂度：O(n^2)
 * @author linmin
 * @date 2021/7/13
 */
public class Solution18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; ++i) {
            if (i > 0 && i < n && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < n; ++j) {
                if (j > i + 1 && j < n && nums[j] == nums[j - 1]) continue;
                int k = j + 1, p = n - 1;
                while (k < p) {
                    while (k > j + 1 && k < n && nums[k] == nums[k - 1]) k++;
                    if (k >= p) break;
                    int sum = nums[i] + nums[j] + nums[k] + nums[p];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k], nums[p]));
                        k++;
                    } else if (sum < target) k++;
                    else p--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        Solution18 s = new Solution18();
        List<List<Integer>> res = s.fourSum(nums, target);
        for (int i = 0; i < res.size(); i++) {
            List<Integer> list = res.get(i);
            list.forEach(System.out::print);
            System.out.println();
        }
    }
}
