package com.linfafa.datastructure.array;

import java.util.Arrays;

/**
 * 题目：螺旋矩阵
 * 难度：中等
 * 题目描述：给你一个正整数n，生成一个包含1到n*n 所有元素，
 * 且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 * @author linmin
 * @date 2021/5/31
 */
public class Solution59 {
    public int[][] generateMatrix(int n) {
        int[][] res=new int[n][n];
        int startx=0,starty=0;
        int loop=n/2;//每个圈循环几次，若为奇数次，需要为矩阵中心补上最后一个数
        int mid=n/2; //矩阵中间的位置

        int num=1; //给空格赋值
        int offset=1;//每循环一圈，需要控制每一条边的遍历长度
        int i,j;

        while(loop>=0){
            i=startx;
            j=starty;

            //上行从左到右
            for(j=starty;j<starty+n-offset;j++){
                res[i][j]=num++;
            }
            //右列从上到下
            for(i=startx;i<startx+n-offset;i++){
                res[i][j]=num++;
            }
            //下行从右到左
            for(;j>starty;j--){
                res[i][j]=num++;
            }
            //左列从下到上
            for(;i>startx;i--){
                res[i][j]=num++;
            }
            startx++;
            starty++;

            offset+=2;

            loop--;
        }

        if(n%2==1){
            res[mid][mid]=n*n;
        }

        return res;
    }

    public static void main(String[] args) {
        Solution59 s = new Solution59();
        int[][] res = s.generateMatrix(4);
        for(int i=0;i<res.length;i++){
            for(int j=0;j<res[0].length;j++){
                System.out.print(res[i][j]+",");
            }
            System.out.println();
        }
    }
}
