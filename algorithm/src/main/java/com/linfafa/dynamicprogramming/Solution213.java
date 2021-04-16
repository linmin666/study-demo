package com.linfafa.dynamicprogramming;

/**
 * 🌟🌟🌟🌟
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时相邻的房屋装有相互连通的防盗系统,如果两间相邻的房屋在同一晚上被小偷闯入,系统会自动报警 。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组,计算你在不触动警报装置的情况下,能够偷窃到的最高金额。
 * 输入：nums = [2,3,2]
 * 输出：3
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * <p>
 * 转移方程：dp[i]=max(dp[i-1],dp[i-2]+nums[i]),
 * 边界条件：
 * dp[start]=num[start]; --只有一个房间
 * dp[start+1]=max(num[start],num[start+1] --有两个房间时
 * (start,end)取值分别为(0,n-2),(1,n-1)
 * <p>
 * 【注意】这种边界问题的题都可以参考这几种思想：
 * （1）前后填充
 * （2）分两次，限制不同的边界，比较这两种结果
 * <p>
 * 1 <= nums.length <= 100
 */
public class Solution213 {
    /**
     * 方法一 使用两个数组，一个表示不强第一家，一个表示不强最后一家，然后比较这两种方式那种值更大
     * 时间复杂度：O(n)
     */
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        return Math.max(robValue(nums, 0, nums.length - 2),
                robValue(nums, 1, nums.length - 1));
    }

    int robValue(int[] nums, int start, int end) {
        int first = nums[start];
        int second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        Solution213 s = new Solution213();
        int res = s.rob(nums);
        System.out.println(res);
    }
}
