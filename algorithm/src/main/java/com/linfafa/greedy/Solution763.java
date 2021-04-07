package com.linfafa.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
 * 返回一个表示每个字符串片段的长度的列表。
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 */
public class Solution763 {
    public List<Integer> partitionLabels(String S) {
        ArrayList<Integer> list = new ArrayList<>();
        int[] lastIndexs=new int[26];//下标表示26个字母，值表示lastIndex
        for(int i=0;i<S.length();i++){
            lastIndexs[S.charAt(i)-'a']=i;
        }
        int start=0,end=0;
        for(int i=0;i<S.length();i++){
            end=Math.max(end,lastIndexs[S.charAt(i)-'a']);
            if(i==end){//满足切分条件
                list.add(end-start+1);
                start=end+1;
            }
        }
         return list;
    }

    public static void main(String[] args) {
        String str="ababcbacadefegdehijhklij";
        Solution763 s = new Solution763();
        List<Integer> list = s.partitionLabels(str);
        list.stream().forEach(System.out::println);
    }
}
