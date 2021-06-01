package com.linfafa.search.binary;

/**
 * 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数.（n>=1）
 */
public class Solution540 {
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (mid - 1 >= 0 && nums[mid - 1] == nums[mid]) {//mid和左边的数相等
                if (mid % 2 == 1) {//如3,3,7,7,10,11,11
                    left = mid + 1;
                } else {//如1,1,2,3,3,4,4,5,5
                    right = mid - 2;
                }
            } else if (mid + 1 < nums.length && nums[mid] == nums[mid + 1]) {//mid和右边的数相等
                if (mid % 2 == 1) {//如1,1,2,3,3,4,4
                    right = mid - 1;
                } else {//如1,1,2,2,3,3,4,5,5
                    left = mid + 2;
                }
            } else return nums[mid];
        }
        return nums[left];
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2,2,4,4,5,5,9};
        Solution540 s = new Solution540();
        int res = s.singleNonDuplicate(nums);
        System.out.println(res);
    }
}
