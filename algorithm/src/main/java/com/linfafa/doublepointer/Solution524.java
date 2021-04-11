package com.linfafa.doublepointer;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。
 * 如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。
 * 输入:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * 输出:
 * "apple"
 */
public class Solution524 {
    public String findLongestWord(String s, List<String> dictionary) {
        String res="";
        //遍历字典
        for (String t : dictionary) {
            //判断t是否为s的子序列
            if(isSubsequence(t,s)){
                //比较当前结果与t，取最优的结果
                if(res.length()<t.length()||(res.length()==t.length() && res.compareTo(t)>0))
                    res=t;
            }
        }
        return res;
    }

    boolean isSubsequence(String t,String s){
       int indexT=0,indexS=0;
       while(indexT<t.length() && indexS<s.length()){
           if(t.charAt(indexT)==s.charAt(indexS)){
               indexT++;
           }
           indexS++;
       }
        return indexT==t.length();
    }

    public static void main(String[] args) {
        List<String> dic=new ArrayList<>();
        dic.add("apple");
        dic.add("avffgr");
        String str="avffgrpple";
        Solution524 s = new Solution524();
        String res=s.findLongestWord(str,dic);
        System.out.println(res);
    }
}
