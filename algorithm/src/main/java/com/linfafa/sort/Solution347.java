package com.linfafa.sort;

import scala.Int;

import java.util.*;

/**
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 解题思路：桶排序，为每个出现的元素都设立一个桶，桶内记录元素出现的次数，然后对桶进行排序。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 */
public class Solution347 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> countMap=new HashMap<>();
        for(int num:nums){
            countMap.put(num,countMap.getOrDefault(num,0)+1);
        }
        int[][] numsFrequent=new int[countMap.size()][2];//numsFrequent[i][0]表示对应的数字,numsFrequent[i][1]表示频次
        int i=0;
        for(Map.Entry<Integer,Integer> entry:countMap.entrySet()){
            numsFrequent[i][0]=entry.getKey();
            numsFrequent[i][1]=entry.getValue();
            i++;
        }

        Arrays.sort(numsFrequent, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1]-o1[1];
            }
        });
        int[] res=new int[k];
        for(int j=0;j<k;j++){
            res[j]=numsFrequent[j][0];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1};
        int k = 1;
        Solution347 s = new Solution347();
        int[] res = s.topKFrequent(nums,k);
        Arrays.stream(res).forEach(System.out::print);
    }
}
