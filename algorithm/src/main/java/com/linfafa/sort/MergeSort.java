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
        int p = left;//p是指向[left,mid)的指针
        int q = mid;//q是指向[mid,right)的指针
        int i = left;//i用于指向temp中写入的index

        //p在[left,mid)范围内或者q在[mid,right)范围内（即还有元素没有放入temp数组）
        while (p < mid || q < right) {
            //1.q>=right(指右半边已经都有数据了，都写进temp数组里了);
            //2.左边的元素还没写完，且左边小于右边
            if (q >= right || (p < mid && nums[p] <= nums[q])) {

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
