package com.linfafa.dp;

/**
 * 编写一个高效的算法来判断m x n矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 */
public class Solution74 {
    public boolean searchMatrix(int[][] matrix, int target) {//把矩阵看着1～n行拼成有序数组，用二分查找
        int h = matrix.length;//矩阵的长度
        int l = matrix[0].length;//矩阵的宽度
        int fromIndex = 0;
        int endIndex = matrix[0].length * matrix.length - 1;
        while (endIndex >= fromIndex) {
            int mid = (endIndex + fromIndex) / 2;
            int val = matrix[mid / l][mid % l];
            if (target < val) endIndex = mid - 1;
            else if (target > val) fromIndex = mid + 1;
            else return true;
        }
        return false;
    }


    public static void main(String[] args) {
        //[[1,3,5,7],[10,11,16,20],[23,30,34,60]]
        //3
        Solution74 s = new Solution74();
        int[][] matrix = new int[][]{{2}, {3}};
        int target = 2;
//        System.out.println(s.search(matrix[0],target,0,0));
//        int[][] matrix=new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}};

        boolean b = s.searchMatrix(matrix, target);
        System.out.println(b);
    }
}
