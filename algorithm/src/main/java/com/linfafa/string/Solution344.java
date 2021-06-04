package com.linfafa.string;

/**
 * 题目：反转字符串
 * 难度：简单
 * 题目描述：编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 *
 * @author linmin
 * @date 2021/6/1
 */
public class Solution344 {
    public void reverseString(char[] s) {
        int n=s.length-1;
        for(int i=0;i<=n/2;++i){
            swap(s,i,n-i);
        }
    }
    void swap(char[] s,int i,int j){
        char temp=s[i];
        s[i]=s[j];
        s[j]=temp;
    }

    public static void main(String[] args) {
        char[] chars={'a'};
        Solution344 s = new Solution344();
        s.reverseString(chars);
        for (char c : chars) {
            System.out.print(c);
        }

    }
}
