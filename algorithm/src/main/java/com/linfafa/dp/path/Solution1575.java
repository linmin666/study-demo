package com.linfafa.dp.path;

import java.util.Arrays;

/**
 * 题目：统计所有可行路径
 * 难度：困难🌟🌟🌟
 * 题目描述：给你一个互不相同的整数数组，其中locations[i]表示第i个城市的位置。
 * 同时给你start，finish和fuel分别表示出发城市、目的地城市和你初始拥有的汽油总量。
 * 每一步中，如果你在城市i，你可以选择任意一个城市j，满足j != i且0 <= j < locations.length，
 * 并移动到城市j。从城市i移动到j消耗的汽油量为|locations[i] - locations[j]|，|x|表示x的绝对值。
 * <p>
 * 请注意，fuel任何时刻都不能为负，且你可以经过任意城市超过一次（包括start和finish）。
 * 请你返回从start到finish所有可能路径的数目。
 * 由于答案可能很大，请将它对10^9+7取余后返回
 * <p>
 * 解题思路：
 * <p>
 * 参考宫水三叶的题解
 *
 * @author linmin
 * @date 2021/6/8
 */
public class Solution1575 {
    int mod = 1000000007;

    /**
     * 「记忆话搜索」转换为「动态规划」
     * dp[i][fuel],i表示当前位置（对应location的下标），fuel表示剩余油量；
     * 值表示从i出发，到达end的路径数量。
     * <p>
     * 状态转移方程：
     * dp[i][fuel]=dp[i][fuel]+dp[k][fuel-need]
     * k表示计算位置i油量fuel的状态时枚举的「下一位置」，need表示从i到k需要的油量。
     */
    public int countRoutes1(int[] locations, int start, int finish, int fuel) {
        int n = locations.length;
        int[][] dp = new int[n][fuel + 1];
        for (int i = 0; i <= fuel; ++i) dp[finish][i] = 1;//当前位置为finish，路径均为1
        for (int cur = 0; cur <= fuel; cur++) {
            for (int i = 0; i < n; i++) {
                for (int k = 0; k < n; k++) {
                    if (i != k) {
                        int need = Math.abs(locations[i] - locations[k]);
                        if (cur >= need) {
                            dp[i][cur] += dp[k][cur - need];
                            dp[i][cur] %= mod;
                        }
                    }
                }
            }
        }

        return dp[start][fuel];
    }

    //cache[i][fuel]表示从i出发，当前剩余油量为fuel的前提下，到达目标位置的「路径数量」
    int[][] cache;

    /**
     * 记忆化搜索，DFS通常有一下几个步骤：
     * （1）设计好递归函数的「入参」和「出参」
     * （2）设置好递归函数的出口（Base Case）
     * （3）编写「最小单元」处理逻辑
     * 所谓找Base Case，其实就是确定什么样的情况下，算一次有效/无效。
     * 对于本题，Base Case就是确定什么情况下算0条路径，什么情况下算1条路径。
     * <p>
     * 有效情况：当前所处的位置就是目的地finish时，就算成有效路径；
     * 无效情况：a.当油量耗完，所处位置又不在finish，那么就算走到头了，算无效情况，可以终止递归；
     * b.油未耗完，但无法到达目的地；
     * <p>
     * 缓存：cache[i][fuel]表示从i出发，当前剩余油量为fuel的前提下，到达目标位置的「路径数量」。
     * （在i和fuel确定的情况下，其到达目的地的路径数量是唯一确定的）
     */
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int n = locations.length;
        cache = new int[n][fuel + 1];
        for (int i = 0; i < n; i++) {
            //初始化为-1，以区分0条路径和未被计算
            Arrays.fill(cache[i], -1);
        }

        return dfs(locations, start, finish, fuel);
    }

    /**
     * 计算路径数量
     *
     * @param locations 入参locations
     * @param u         当前所在位置(ls的下标)
     * @param end       目的地位置(ls的下标)
     * @param fuel      剩余油量
     * @return 在位置u出发，油量为fuel的情况下，到达end的路径数量
     */
    int dfs(int[] locations, int u, int end, int fuel) {
        //若缓存中已有答案，直接返回
        if (cache[u][fuel] != -1) return cache[u][fuel];

        int n = locations.length;
        //base case 1:油量为0且不在目的地,缓存结果0并返回
        if (fuel == 0 && u != end) {
            cache[u][fuel] = 0;
            return 0;
        }

        //base case 2:油量不为0，且无法到达任何位置
        boolean hasNext = false;
        for (int i = 0; i < n; ++i) {
            if (i != u) {
                int need = Math.abs(locations[u] - locations[i]);
                if (fuel >= need) {
                    hasNext = true;
                    break;
                }
            }
        }
        if (fuel != 0 && !hasNext) {
            cache[u][fuel] = u == end ? 1 : 0;
            return cache[u][fuel];
        }

        //计算油量为fuel，从u到end的路径数量
        //由于每个点都可以经过多次，如果u=end，那么本身就算一条路径
        int sum = u == end ? 1 : 0;
        for (int i = 0; i < n; ++i) {
            if (i != u) {
                int need = Math.abs(locations[i] - locations[u]);
                if (fuel >= need) {
                    sum += dfs(locations, i, end, fuel - need);
                    sum %= mod;
                }
            }
        }
        cache[u][fuel] = sum;
        return sum;
    }

    /**
     * 计算路径数量
     * 优化：如果一步到达不了，说明从位置u无法到达end位置
     *
     * @param locations 入参locations
     * @param u         当前所在位置(ls的下标)
     * @param end       目的地位置(ls的下标)
     * @param fuel      剩余油量
     * @return 在位置u出发，油量为fuel的情况下，到达end的路径数量
     */
    int dfs1(int[] locations, int u, int end, int fuel) {
        //若缓存中已有答案，直接返回
        if (cache[u][fuel] != -1) return cache[u][fuel];

        //如果一步到达不了，说明从u无法到达end
        int need = Math.abs(locations[u] - locations[end]);
        if (need > fuel) {
            cache[u][fuel] = 0;
            return 0;
        }

        int n = locations.length;

        //计算油量为fuel，从u到end的路径数量
        //由于每个点都可以经过多次，如果u=end，那么本身就算一条路径
        int sum = u == end ? 1 : 0;
        for (int i = 0; i < n; ++i) {
            if (i != u) {
                need = Math.abs(locations[i] - locations[u]);
                if (fuel >= need) {
                    sum += dfs(locations, i, end, fuel - need);
                    sum %= mod;
                }
            }
        }
        cache[u][fuel] = sum;
        return sum;
    }


    public static void main(String[] args) {
        int[] locations = {2, 3, 6, 8, 4};
        int start = 1, finish = 3, fuel = 5;

        Solution1575 s = new Solution1575();
        int res = s.countRoutes(locations, start, finish, fuel);
        System.out.println(res);
    }
//    输入：locations = [2,3,6,8,4], start = 1, finish = 3, fuel = 5
//    输出：4
}
