package com.linfafa.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
 * 每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 * 请你重新构造并返回输入数组people 所表示的队列。返回的队列应该格式化为数组 queue ，
 * 其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 */
public class Solution406 {
    public int[][] reconstructQueue(int[][] people) {

        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {//按h升序，按k降序
                if(o1[0]==o2[0])return o2[1]-o1[1];
                return o1[0]-o2[0];
            }
        });
        for(int i=people.length-1;i>=0;i--){
            if(people[i][1]>0){
                insert(people,i,i+people[i][1]);
            }
        }
        return people;
    }

    void insert(int[][] nums,int i,int j){
        int[] temp=nums[i];
        while(i<j){
            nums[i]=nums[i+1];
            i++;
        }
        nums[j]=temp;
    }

    public static void main(String[] args) {
        int[][] people={{6,0},{5,0},{4,0},{3,2},{2,2},{1,4}};
        Solution406 s = new Solution406();
        int[][] res = s.reconstructQueue(people);
        Arrays.stream(res).forEach(x-> System.out.println("["+x[0]+","+x[1]+"]"));
    }
}
