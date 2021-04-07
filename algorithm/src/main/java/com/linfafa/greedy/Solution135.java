package com.linfafa.greedy;

import java.util.Arrays;

/**
 * 135.分发糖果
 * 老师想给孩子们分发糖果，有N个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * 每个孩子至少分配到1个糖果。
 * 评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 */
public class Solution135 {
    public int candy(int[] ratings) {
        if (ratings.length <= 1) return ratings.length;
        int[] nums = new int[ratings.length];
        for(int i=0;i<nums.length;i++)nums[i]=1;

        //正向遍历孩子，如果右边孩子比左边孩子评分高，右边孩子糖果数=左边孩子糖果数+1
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i - 1] < ratings[i]) nums[i] = nums[i - 1] + 1;
        }
        //倒着遍历孩子，如果左边孩子比右边孩子评分高，若左边孩子的糖果数低于右边孩子的糖果数，则左边孩子的糖果数=右边孩子的糖果数+1
        for (int i = ratings.length - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i] ) nums[i - 1] = Math.max(nums[i-1],nums[i] + 1);
        }

        return Arrays.stream(nums).sum();
    }

    public static void main(String[] args) {
        int[] ratings=new int[]{1,2,2};
        Solution135 s = new Solution135();
        int res = s.candy(ratings);
        System.out.println(res);

    }

}
