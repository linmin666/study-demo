package com.linfafa.ch2;

/**
 * @author linmin
 * @date 2022/3/22
 */
public class Demo1_2 {
    private static int count;

    public static void main(String[] args) {
        try {
        method1();
        }catch (Throwable e){
            e.printStackTrace();
            System.out.println(count);
        }
    }

    public static void method1(){
        count++;
        method1();
    }
}
