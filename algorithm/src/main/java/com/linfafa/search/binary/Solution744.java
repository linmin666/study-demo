package com.linfafa.search.binary;

/**
 * 题目：寻找比目标字母大的最小字母
 * 难度：简单
 * 题目描述：给你一个排序后的字符列表letters，列表中只包含小写英文字母。
 * 另给出一个目标字母target，请你寻找在这一有序列表里比目标字母大的最小字母。
 * 在比较时，字母是依序循环出现的。举个例子：
 * 如果目标字母 target = 'z' 并且字符列表为letters = ['a', 'b']，则答案返回'a'
 *
 * @author linmin
 * @date 2021/5/28
 */
public class Solution744 {
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0, right = letters.length;
        while (left < right) {  //[left,right)
            int mid = left + (right - left) / 2;
            if (target >= letters[mid]) {  //[mid+1,right),若target>=letters[mid]则往右移
                left = mid + 1;
            } else right = mid;//[left,mid)
            System.out.println("left=" + left + ",right=" + right);
        }
        return letters[right % letters.length];
    }

    public static void main(String[] args) {
        char[] letters = {'e', 'e', 'e', 'e', 'e', 'e', 'n', 'n', 'n', 'n'};
        char target = 'e';
        Solution744 s = new Solution744();
        char res = s.nextGreatestLetter(letters, target);
        System.out.println(res);
    }
}
