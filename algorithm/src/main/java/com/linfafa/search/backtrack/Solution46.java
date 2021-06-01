package com.linfafa.search.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：全排列
 * 难度：中等
 * 题目描述：给定一个不含重复数字的数组nums，返回其所有可能的全排列。你可以按任意顺序返回答案。
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * 解题思路：深度优先搜索+回溯法
 *
 * @author linmin
 * @date 2021/5/11
 */
public class Solution46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtracking(nums, 0, res);
        return res;
    }

    private void backtracking(int[] nums, int level, List<List<Integer>> res) {
        if (level == nums.length - 1) {
            System.out.println("level=" + level + ",记录结果：" + nums[0] + nums[1] + nums[2]);
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            res.add(list);
            return;
        }
        for (int i = level; i < nums.length; ++i) {
            System.out.println("level=" + level + ",修改当前节点状态");
            swap(nums, level, i);   //修改当前节点状态
            backtracking(nums, level + 1, res); //递归子节点
            swap(nums, level, i);   //回改当前节点状态
            System.out.println("level=" + level + ",回改当前节点状态");
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Solution46 s = new Solution46();
        List<List<Integer>> res = s.permute(nums);
    }

}
