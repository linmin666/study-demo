package com.linfafa.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 算法思想：
 * 如果要排序数组中p...r之间的一组数据，我们选择p到r之间的任意一点作为pivot（分区点），
 * 我们遍历p到r之间的数据，把小于pivot的放在左边，把大于pivot的放到右边，把pivot放到中间。
 * 根据分治、递归的处理思想，我们可以递归排序下标从
 * 递推公式：quick_sort(p...r)=quick(p...q)+quick_sort(q+1...r),终止条件是p>=r。
 */
public class QuickSort {
    public void quickSort(int nums[],int p,int r) {
        //终止条件
        if(p>=r)return;
        //分区点
        int q = partition(nums, p, r);
        quickSort(nums,p,q-1);
        quickSort(nums,q+1,r);

    }

    /**
     * 随机选择一个元素作为pivot(一般 情况下，可以选择p到r区间的最后一个元素)，然后对A[p...r]分区，函数返回pivot的下标。
     */
    int partition(int[] nums,int p,int r){
        int pivot=nums[r];
        int i=p;
        for(int j=p;j<r;j++){
            if(nums[j]<pivot){//小于pivot，放左边
                swap(nums,i,j);
                i++;
            }
        }
        swap(nums,i,r);//将pivot放置到中间
        return i;
    }


    void swap(int[] nums,int a,int b){
        int temp=nums[a];
        nums[a]=nums[b];
        nums[b]=temp;
    }
    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] nums={4,2,3,7,5,9};
        quickSort.quickSort(nums,0,nums.length-1);
        Arrays.stream(nums).forEach(System.out::print);
    }
}
