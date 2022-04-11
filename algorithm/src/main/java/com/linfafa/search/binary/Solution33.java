package com.linfafa.search.binary;

/**
 * 题目：搜索旋转排序数值
 * 难度：中等🌟🌟🌟
 * 描述：升序排列的整数数组nums在预先未知的某点上进行了旋转。
 * 例如，[0,1,2,4,5,6,7]经旋转后可能变成[4,5,6,7,0,1,2]
 * 请你在数组中搜索target，如果数组中存在这个目标值，
 * 则返回它的索引，否则返回-1。
 * 思路：经过旋转后的数组，满足数组下标[0,旋转点]的值>=nums[0];
 * 数组下表[旋转点,len)的值不满足>=nums[0]
 *
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2],target=0
 * 输出：4
 * <p>
 * 1 <= nums.lenght <= 100
 * 0 <= nums[i] <= 100
 *
 * @author linmin
 * @date 2021/8/17
 */
public class Solution33 {
    public static void main(String[] args) {
        int[] nums={4,5,6,7,0,1,2};
//        int[] nums = {1, 3};
        Solution33 s = new Solution33();
        int res = s.search(nums, 0);
        System.out.println(res);

    }

    /**
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;
        if (n == 1) return nums[0] == target ? 0 : -1;

        //第一次二分：找旋转点
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (nums[mid] >= nums[0]) {
                left = mid;
            }else right=mid-1;
        }
        //通过target和nums[0]对比，确定二分查找是在哪个范围
        if (target >= nums[0])
            left = 0;
        else {
            left = left + 1;
            right = n - 1;
        }

        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (nums[mid] <= target)
                left = mid;
            else
                right = mid - 1;
        }
        return nums[right] == target ? right : -1;
    }

}
