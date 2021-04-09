package com.linfafa.greedy;

/**
 * 给你一个长度为n的整数数组，请你判断在 最多 改变1 个元素的情况下，该数组能否变成一个非递减数列。
 * <p>
 * 我们是这样定义一个非递减数列的：对于数组中任意的i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 */
public class Solution665 {
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        if (nums.length < 2) return true;
        for (int i = 1; i < nums.length && count<2; i++) {
           if(nums[i]<nums[i-1]){
               if(count==0){//第一次，选择修改一个数
                    if(i-2>=0 &&nums[i-2]>nums[i]){//当前面第二位存在且大于当前位时
                        nums[i]=nums[i-1];
                    }else{//不存在前面第二位或前面第二位小于等于当前位时
                        nums[i-1]=nums[i];
                    }
               }
               count++;//记录出现降序的次数
           }

        }
        return count < 2;
    }

    public static void main(String[] args) {
        /*
        4，2，3
	-1，4，2，3
	2，3，3，2，4
         */
        int[] nums = {2,3,3,2,4};
        Solution665 s = new Solution665();
        boolean flag = s.checkPossibility(nums);
        System.out.println(flag);
    }
}
