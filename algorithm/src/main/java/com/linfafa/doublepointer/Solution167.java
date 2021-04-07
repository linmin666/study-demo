package com.linfafa.doublepointer;

/**
 * 给定一个已按照 升序排列的整数数组numbers ，请你从数组中找出两个数满足相加之和等于目标数target 。
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，
 * 所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
 */
public class Solution167 {
    public int[] twoSum(int[] numbers, int target) {
        int left=0,right=numbers.length-1;
        while (left<right){
            if(numbers[left]+numbers[right]==target){
                return new int[]{left+1,right+1};//下标从1开始计数
            }else if(numbers[left]+numbers[right]>target){
                right--;
            }else left++;

        }
        return null;
    }

    public static void main(String[] args) {
        int[] numbers={};
//        int[] numbers = {2,3,4,15};
        int target = 6;
        Solution167 s = new Solution167();
        int[] res = s.twoSum(numbers, target);
        if(res!=null) System.out.println(res[0]+"+"+res[1]);
    }
}
