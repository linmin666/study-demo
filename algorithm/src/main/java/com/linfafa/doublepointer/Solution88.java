package com.linfafa.doublepointer;

import java.util.Arrays;

/**
 * 给你两个有序整数数组nums1 和 nums2，请你将 nums2 合并到nums1中，使 nums1 成为一个有序数组。
 * 初始化nums1 和 nums2 的元素数量分别为m 和 n 。你可以假设nums1 的空间大小等于m + n，这样它就有足够的空间保存来自 nums2 的元素。
 */
public class Solution88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = nums1.length - 1;//用于指向放置位置
        n--;
        m--;

        while (n >= 0) {
            //如果数组1中最后一个数大于数组2中最后一个数，就把数组1的最后一个数放入最后，否则就把数组2的最后一个数放入最后
            if (m >= 0 && nums1[m] > nums2[n]) {
                //交换nums1[i]和nums1[m]
                nums1[i--]=nums1[m];
                nums1[m--]=0;
            }else{
                //交换nums1[i]和nums2[n];
                nums1[i--]=nums2[n--];
            }
        }
    }

    public static void main(String[] args) {
        //输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
       int[] nums1=new int[]{1,2,3,0,0,0};
       int[] nums2=new int[]{2,5,6};
        Solution88 s = new Solution88();
        s.merge(nums1,3,nums2,3);
        Arrays.stream(nums1).forEach(System.out::print);
    }
}
