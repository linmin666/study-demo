package com.linfafa.sort;

/**
 * 在未排序的数组中找到第k个最大的元素。请注意，你需要找的是数组排序后的第k个最大的元素，而不是第k个不同的元素。
 * 提示：你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 * 解题思路：快排思想
 */
public class Solution215 {
    public int findKthLargest(int[] nums, int k) {
        return quikSort(nums, 0, nums.length - 1, nums.length - k);
    }

    int quikSort(int[] nums, int p, int r, int index) {
        int q = partition(nums, p, r);
        if (q == index)
            return nums[q];
        else if (q > index)
            return quikSort(nums, p, q - 1, index);
        else
            return quikSort(nums, q + 1, r, index);
    }

    int partition(int[] nums, int p, int r) {
        int pivot = nums[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, r);
        return i;
    }

    void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        //输入: [3,2,1,5,6,4] 和 k = 2
        //输出: 5
        int[] nums = {2, 1};
        Solution215 s = new Solution215();
        int res = s.findKthLargest(nums, 1);
        System.out.println(res);
    }
}
