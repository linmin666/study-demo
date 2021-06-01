package com.linfafa.datastructure.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在answers数组里。
 * 返回森林中兔子的最少数量。
 * 举例：输入: answers = [1, 1, 2]，输出: 5
 * 解题思路：
 * （1）同颜色的兔子回答的数值是相同的；（2）但回答相同数值的兔子不一定是相同颜色的兔子；
 * 设某种颜色的兔子为m只，它们回答的数组是cnt，m=cnt+1;
 * 但在answers中，回答cnt的数量为t的话，
 * (1) t <= cnt+1; 这t个数值可以认为是同一颜色的cnt+1只兔子；
 * (2) t > cnt+1; 说明不止一种颜色，尽可能使不同颜色的数量达到cnt+1；
 */
public class Solution781 {
    public int numRabbits(int[] answers) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();//key存answers的不同数值，value存数值出现的次数；
        for (int num : answers) {
            if (!map.containsKey(num)) map.put(num, 1);
            else map.put(num, map.get(num) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int t = entry.getValue();
            int cnt = entry.getKey();
            if (t <= (cnt + 1)) res += cnt+1;
            else {//将t尽可能的分为cnt+1
                if(t%(cnt+1)>0){
                    res+=(cnt+1)*(t/(cnt+1)+1);
                }else res+=t;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] answers=new int[]{10,10,10};
//        int[] answers = new int[]{0, 0, 0,1,0};
        Solution781 s = new Solution781();
        int res = s.numRabbits(answers);
        System.out.println(res);
    }
}
