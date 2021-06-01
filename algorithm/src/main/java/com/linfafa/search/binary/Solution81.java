package com.linfafa.search.binary;

/**
 * 搜索旋转排序数组 II
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 */
public class Solution81 {
    public boolean search(int[] nums, int target) {
        if (nums.length == 0) return false;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) return true;//查找到了
            if (nums[mid] == nums[left]) {//无法判断，就让left向右移一位
                left++;
                continue;
            } else if (nums[left] < nums[mid]) {//左半边有序
                if (target >= nums[left] && target < nums[mid]) //target在左半边
                    right = mid - 1;
                else //target在右半边
                    left = mid + 1;
            } else {//右半边有序
                if (target > nums[mid] && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;


            }
        }
        return false;
    }

    public static void main(String[] args) {
        //nums = [2,5,6,0,0,1,2], target = 0
        int[] nums = new int[]{2, 5, 6, 0, 0, 1, 2};
        int target = 0;
        Solution81 s = new Solution81();
        boolean res = s.search(nums, target);
        System.out.println(res);
    }
}
