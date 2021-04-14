package com.linfafa.sort;

import java.util.Arrays;

/**
 * 归并排序
 * 如果要排序一个数组，我们先把数组从中间分成前后两部分，然后对前后两部分分别排序，再将排好序的两部分合并在一
 * 起，这样整个数组就都有序了.
 * 递推公式：merge_sort(p...r)=merge(merge_sort(p...q),merge_sort(q+1...r)),终止条件是p>=r
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(n)
 */
public class MergeSort {
    public void mergeSort(int[] nums, int left, int right, int[] temp) {
        if (left + 1 >= right) return;
        //divide
        int mid = (left + right) / 2;
        mergeSort(nums, left, mid, temp);
        mergeSort(nums, mid, right, temp);
        //conquer
        int p = left, q = mid, i = left;
        while (p < mid || q < right) {
            if (q >= right || (p < mid && nums[p] <= nums[q])) {//1.q>=right(只存在一个元素或者不存在元素);2.存在元素且左边小于右边
                temp[i++] = nums[p++];
            } else {
                temp[i++] = nums[q++];
            }
        }
        //将[left,right)写入原数组
        for(int j=left;j<right;j++){
            nums[j]=temp[j];
        }
    }

    public static void main(String[] args) {
        int[] nums={4,2,3,7,5,9};
        MergeSort mergeSort = new MergeSort();
        int[] temp=new int[nums.length];
        mergeSort.mergeSort(nums,0,nums.length-1,temp);
        Arrays.stream(nums).forEach(System.out::print);
    }
}
