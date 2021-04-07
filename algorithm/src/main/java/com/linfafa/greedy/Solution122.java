package com.linfafa.greedy;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 输入: [1,2,3,4,5]
 * 输出: 4
 */
public class Solution122 {
    public int maxProfit(int[] prices) {
        int res=0;
        for(int i=1;i<prices.length;i++){//只要今天比昨天大就卖出
          if(prices[i]>prices[i-1])
              res+=prices[i]-prices[i-1];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] prices={1,2,3,4,5};
        Solution122 s = new Solution122();
        int res = s.maxProfit(prices);
        System.out.println(res);
    }

}
