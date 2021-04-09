package com.linfafa.search;

/**
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
 * 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]]
 * 请你找出并返回数组中的 最小元素 。
 */
public class Solution154 {
    public int findMin(int[] nums) {
        int left=0,right=nums.length-1;
        while (left<right){
            int mid=(left+right)/2;
            if(nums[mid]<nums[right])//右边升序，找左边
                right=mid;
            else if(nums[mid]>nums[right])//左边升序，找右边
                left=mid+1;
            else //nums[mid]==nums[right]，无法判断，去掉最后那位再做比较
            right--;//
        }

        return nums[left];
    }

    public static void main(String[] args) {
        int[] nums={1,3,3};
        Solution154 s = new Solution154();
        int min = s.findMin(nums);
        System.out.println(min);
    }
}
