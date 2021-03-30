package com.linfafa.c12;

import org.openjdk.jol.info.ClassLayout;

/**
 * 双锁检测单例
 * 2.volatile的第二项语义：禁止指令重排序优化
 */
public class Singleton {
    private volatile static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton s=Singleton.getInstance();
        String layout = ClassLayout.parseInstance(s).toPrintable();
        System.out.println(layout);
    }
}
