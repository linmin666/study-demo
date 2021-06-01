package com.linfafa.datastructure.hash;

/**
 * 题目：有效的字母异位词
 * 难度：简单
 * 题目描述：给定两个字符串s和t，编写一个函数来判断t是否是s的字母异位词。
 * @author linmin
 * @date 2021/5/31
 */
public class Solution242 {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length())return false;
        int[] records=new int[26];//下标表示字母index，value表示出现的次数
        for(int i=0;i<s.length();++i){
            records[s.charAt(i)-'a']++;
        }
        for(int j=0;j<t.length();++j){
            records[t.charAt(j)-'a']--;
        }
        for(int k=0;k<26;++k){
            if(records[k]!=0)return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s="rat";
        String t="car";
        Solution242 solution = new Solution242();
        boolean res = solution.isAnagram(s, t);
        System.out.println(res);

    }

}
