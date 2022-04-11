package com.linfafa.ch2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linmin
 * @date 2022/3/22
 */
public class Demo1_5 {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        String a = "hello";
        while (true) {
            list.add(a);
            a = a + a;
            i++;
            System.out.println(i);
        }

    }
}
