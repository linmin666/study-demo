package com.linfafa.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目：三数之和
 * 难度：中等
 * 描述：给你一个包含n个整数的数组nums，判断nums中是否存在三个元素a,b,c，使得a+b+c=0?
 * 请找出所有和为0且不重复的三元组。
 * 示例：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 思路：排序+双指针
 * 时间复杂度：排序的复杂度是O(logN)，i是枚举的，复杂度是O(n),i确定后，j和k对于i而言复杂度是O(n)，
 * 最终时间复杂度为O(n^2)
 * 空间复杂度：O(n^2)
 * @author linmin
 * @date 2021/7/13
 */
public class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        //默认升序排列
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            //当nums[i]=nums[i-1]时，跳过nums[i]，避免重复
            if (i > 0 && i < n && nums[i] == nums[i - 1])continue;
            int j = i + 1, k = n - 1;
            while (j < k) {
                //当nums[i]=nums[i-1]时，跳过nums[i]，避免重复
                while (j > i + 1 && j < n && nums[j] == nums[j - 1]) ++j;
                if (j >= k) break;
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                } else if (sum > 0) k--;
                else j++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        Solution15 s = new Solution15();
        List<List<Integer>> res = s.threeSum(nums);
        for (int i = 0; i < res.size(); i++) {
            List<Integer> list = res.get(i);
            list.forEach(System.out::print);
            System.out.println();
        }
    }
}
