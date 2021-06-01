package com.linfafa.datastructure.hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 题目：两个数组的交集
 * 难度：简单
 * 题目描述：给定两个数组，编写一个函数来计算它们的交集。
 * @author linmin
 * @date 2021/5/31
 */
public class Solution349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set=new HashSet<>();
        Set<Integer> res=new HashSet<>();
        for(int i=0;i<nums1.length;i++){
            set.add(nums1[i]);
        }
        for(int i=0;i<nums2.length;i++){
            if(set.contains(nums2[i])){
                res.add(nums2[i]);
              }
        }
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }

    public static void main(String[] args) {
        int[] nums1={1,2,2,1};
        int[] nums2={3,4};
        Solution349 s = new Solution349();
        int[] res = s.intersection(nums1, nums2);
        Arrays.stream(res).forEach(System.out::print);
    }
}
