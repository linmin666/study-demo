package com.linfafa.search.binary;

/**
 * 题目：搜索插入位置
 * 难度：简单
 * 题目描述：给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
 * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假定数组内无重复数据。
 * <p>
 * 解题思路：本题给定的是一个有序数组，且数组内无重复数据，所以可以使用二分法进行搜索。
 * （若数组中有重复数据，那二分法的结果就不是唯一的了）
 * 关于二分法的边界条件：本题中target是在一个左闭右闭的区间中，也就是[left,right]。
 * 所以while条件中是{@code left<=right}而不是{@code left<right}
 *
 * @author linmin
 * @date 2021/5/27
 */
public class Solution35 {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {//注意，此处条件有等号，在[left,right]区间内
//            int mid = (right + left) / 2;
            int mid = left + (right - left) / 2;//防止整型溢出
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {//target在右区间[mid+1,right]
                left = mid + 1;
            } else right = mid - 1;//target在左区间[left,mid-1]
        }
        //分别处理以下四种情况：
        //1.目标值在数组所有元素之前[0,-1)
        //2.目标值等于数组中某个元素 return mid
        //3.目标值插入数组中的位置[left,right] return right+1;
        //4.目标值在数组所有元素之后[left,right],return right+1;
        return right + 1;
    }

    public int searchInsert2(int[] nums, int target) {
        //将target放在左闭右开的区间[left,right)内
        int left = 0, right = nums.length;//注意right的边界值
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {//target在左区间[left,mid)内
                right = mid;
            } else if (nums[mid] < target) {//target在右区间[mid+1,right)内
                left = mid + 1;
            } else return mid;
        }
        //分别处理以下四种情况：
        //1.目标值在数组所有元素之前[0,0)
        //2.目标等于数组中某个元素 return mid
        //3.目标值插入数组中的位置[left,rigth),return right;
        //4.目标值在数组所有元素之后[left,right),return right;
        return right;
    }

    public static void main(String[] args) {
        int target = -1;
        int[] nums = {0, 1, 3};
        Solution35 s = new Solution35();
        int index = s.searchInsert(nums, target);
       int index2= s.searchInsert2(nums,target);
        System.out.println(index);
        System.out.println(index2);
    }

}
