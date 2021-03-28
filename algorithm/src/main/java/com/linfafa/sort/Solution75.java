package com.linfafa.sort;

import java.util.Arrays;

public class Solution75 {

    /**
     * 75. 颜色分区
     * 给定一个包含红色、白色和蓝色，一共n个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 此题中，我们使用整数 0、1和2分别表示红色、白色和蓝色。
     */
    public void sortColors(int[] nums) {
        int head = 0;//指向头部
        int tail = nums.length - 1;//指向尾部
        int i=0;
        //遇到0就插入到前面，遇到2就插入到后面
        while(i<=tail){
            if (nums[i] == 2) {
                swap(i, tail--, nums);
            } else if (nums[i] == 0) {
                if (head == i) {
                    i++;
                    head++;
                }else {
                    swap(i, head++, nums);
                }
            }else i++;
        }
    }

    void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public static void main(String[] args) {
        Solution75 sort = new Solution75();
        int[] nums = {2, 0, 2, 1, 1, 0};
        sort.sortColors(nums);
        Arrays.stream(nums).forEach(x -> System.out.println(x));
    }
}
