package com.linfafa.ch2;

/**
 * @author linmin
 * @date 2022/3/22
 */
public class Demo1_4 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("1...");
        Thread.sleep(30000);
        byte[] array = new byte[1024 * 1024 * 10]; //10MB
        System.out.println("2...");
        Thread.sleep(30000);
        array = null;
        System.gc();
        System.out.println("3...");
        Thread.sleep(100000000L);
    }
}
