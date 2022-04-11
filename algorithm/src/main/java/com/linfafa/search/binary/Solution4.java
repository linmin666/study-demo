package com.linfafa.search.binary;

import java.util.Arrays;

/**
 * 题目：寻找两个正序数组的中位数
 * 难度：困难
 * 描述：给定一个大小分别是m和n的正序数组nums1和nums2。
 * 请找出并返回这两个正序数组的中位数。
 * 示例：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * <p>
 * 解题思路：
 * 方法一：比较简单的思路是将两个数组合并，然后取中位数。
 * 时间复杂度：合并数组时间复杂度O(m+n),排序时间复杂度O(nlog(m+n)),最终时间复杂度为O(m+n)
 * 空间复杂度：O(m+n)
 *
 * @author linmin
 * @date 2021/7/13
 */
public class Solution4 {

    //合并数组+排序
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] nums = new int[m + n];
        int idx = 0;
        for (int i : nums1) nums[idx++] = i;
        for (int j : nums2) nums[idx++] = j;
        Arrays.sort(nums);

        int mid = (m + n) / 2;
        //当有奇数个数时，中位数为第len/2+1个数，其索引为len/2
        if ((m + n) % 2 != 0)
            return nums[mid];
        //当有偶数个数时，中位数为len/2和len/2+1这两个数的均值
        return (double) nums[mid - 1] / 2 + (double) nums[mid] / 2;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3}, nums2 = {2};
//        int[] nums1 = {1, 2}, nums2 = {3, 4};
        Solution4 s = new Solution4();
        double mid = s.findMedianSortedArrays1(nums1, nums2);
        System.out.println(mid);
    }
}
