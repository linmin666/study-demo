package com.linfafa.dp.bag;


/**
 * 题目：分割等和子集
 * 难度：中等
 * 题目描述：给你一个只包含正整数的非空数组nums。请你判断是否可以将
 * 这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * @author linmin
 * @date 2021/6/9
 */
public class Solution416 {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) sum += num;
        int target = sum / 2;
        if (target * 2 != sum) return false;

        int[][] dp = new int[n][target + 1];
        for (int i = 1; i <= target; ++i) {
            dp[0][i] = i >= nums[0] ? nums[0] : 0;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j <= target; ++j) {
                int no = dp[i - 1][j];
                int yes = j >= nums[i] ? dp[i - 1][j - nums[i]] + nums[i] : 0;
                dp[i][j] = Math.max(no, yes);
            }
        }
        return dp[n - 1][target] == target;
    }

    public boolean canPartition1(int[] nums) {
        int n = nums.length, sum = 0;
        for (int num : nums) sum += num;
        int target = sum / 2;
        if (target * 2 != sum) return false;
        int[] dp = new int[target + 1];
        for (int i = 1; i < n; ++i) {
            for (int j = target; j >= 1; --j) {
                int no = dp[j];
                int yes = j >= nums[i] ? dp[j - nums[i]] + nums[i] : 0;
                dp[j] = Math.max(no, yes);
            }
        }
        return dp[target] == target;
    }

    /**
     * 从最多不超过到恰好
     * dp[i][j]=dp[i-1][j]||dp[i-1][j-nums[i]]
     * dp[i][j]表示考虑前i个数值，其选择数字总和是否恰好为j
     * 哨兵思想：
     * 将物品编号从0开始调整为从1开始，这样就可以增加一个不考虑任何物品的情况。
     * （原本dp[0][x]表示只考虑第一件物品、dp[1][x]表示考虑第一件和第二件物品；
     * 调整后的dp[0][x]表示不考虑任何物品、dp[1][x]表示只考虑第一件物品）
     */
    public boolean canPartition2(int[] nums) {
        int n = nums.length, sum = 0;
        for (int num : nums) sum += num;
        int target = sum / 2;
        if (target * 2 != sum) return false;

        //考虑前i个数值，其选择总和是否恰好为j
        boolean[][] dp = new boolean[n + 1][target + 1];
        dp[0][0] = true;//不考虑任何数值，其总和为0

        for (int i = 1; i <= n; ++i) {
            int t = nums[i - 1];//注意这个index不要取错
            for (int j = 1; j <= target; ++j) {
                boolean no = dp[i - 1][j];
                boolean yes = j >= t ? dp[i - 1][j - t] : false;
                dp[i][j] = no | yes;
            }
        }
        return dp[n][target];
    }

    public boolean canPartition3(int[] nums) {
        int n = nums.length, sum = 0;
        for (int num : nums) sum += num;
        int target = sum / 2;
        if (target * 2 != sum) return false;

        //考虑前i个数值，其选择总和是否恰好为j
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;//不考虑任何数值，其总和为0

        for (int i = 1; i <= n; ++i) {
            int t = nums[i - 1];//注意这个index不要取错
            for (int j = target; j >= 1; --j) {
                boolean no = dp[j];
                boolean yes = j >= t ? dp[j - t] : false;
                dp[j] = no | yes;
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
//        nums = [1,5,11,5]
//        输出：true
        int[] nums = {1, 5, 11, 5};
        Solution416 s = new Solution416();
        boolean res = s.canPartition3(nums);
        System.out.println(res);
    }
}
