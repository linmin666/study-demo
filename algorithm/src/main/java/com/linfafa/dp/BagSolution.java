package com.linfafa.dp;

/**
 * 背包问题
 * 对于一组不同重量、不可分割的物品，我们需要选择一些装入背包，
 * 在满足背包最大重量限制的前提下，背包中物品总重量的最大值是多少呢?
 */
public class BagSolution {
    /**
     * @param weight 物品重量的数组
     * @param n      物品总个数
     * @param w      背包最大承重
     * @return 背包中物品总重量的最大值
     */
    public int knapsack(int[] weight, int n, int w) {
        boolean[][] state = new boolean[n][w + 1];
        state[0][0] = true;//哨兵优化
        if (weight[0] <= w) {
            state[0][weight[0]] = true;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= w; j++) {//不把第i个物品放入背包
                if (state[i - 1][j] == true) state[i][j] = true;
            }
            for (int j = 0; j <= w - weight[i]; j++) {//把第i个物品放入背包
                if (state[i - 1][j] == true) state[i][j + weight[i]] = true;
            }
        }
        for (int i = w; i >= 0; i--) {
            if (state[n - 1][i] == true) {
                return i;
            }
        }
        return 0;
    }

    //优化,降低复杂度
    public int knapsack2(int[] weight, int n, int w) {
        boolean[] state = new boolean[w + 1];
        state[0] = true;
        if (weight[0] <= w) state[weight[0]] = true;
        for (int i = 1; i < n; i++) {
            for (int j = w - weight[i]; j >= 0; j--) {//从state[w-weight[i]]的位置往前找（标记为ture的位置）
                if (state[j] == true) state[j + weight[i]] = true;//把第i个物品放入背包
            }
        }
        for (int i = w; i >= 0; i--) {
            if (state[i] == true) return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        BagSolution bs = new BagSolution();
//        int[] weight=new int[]{2,2,3,4,5};
        int[] weight = new int[]{11, 11, 1, 1, 1};
        int w = 7;
        int res = bs.knapsack2(weight, weight.length, w);
        System.out.println(res);
    }
}
