package com.linfafa.greedy;

import java.util.Arrays;

/**
 * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * 给你一个整数数组flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。
 * 另有一个数n ，能否在不打破种植规则的情况下种入n朵花？能则返回 true ，不能则返回 false。
 */
public class Solution605 {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for(int i=0;i<flowerbed.length;i++){
            if(flowerbed[i]==0) {//当前位置没有种植，判断前后位置是边界或者是否未种花
                if ((i - 1 < 0 || flowerbed[i - 1] == 0) && (i + 1 >= flowerbed.length || flowerbed[i + 1] == 0)) {
                    flowerbed[i]=1;
                    n--;
                }
            }
        }
        return n<=0;
    }

    public static void main(String[] args) {
//        flowerbed = [1,0,0,0，0,1], n = 1
        int[] flowerbed={1,0,0,0,0,1};
        Solution605 s = new Solution605();
        boolean res = s.canPlaceFlowers(flowerbed, 2);
        System.out.println(res);
        Arrays.stream(flowerbed).forEach(System.out::print);
    }
}
