package com.linfafa.sort;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列.
 */
public class Solution451 {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        Stream<Map.Entry<Character, Integer>> sortedMap = map.entrySet().stream().sorted(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        String res = "";
        for (Map.Entry<Character, Integer> entry : sortedMap.collect(Collectors.toList())) {
            char key = entry.getKey();
            int value = entry.getValue();
            while (value > 0) {
                res += key;
                value--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution451 s = new Solution451();
        String res = s.frequencySort("tree");
        System.out.println(res);
    }
}
