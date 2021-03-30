package com.linfafa.c13;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.layouters.Layouter;

public class SychronizedTest {
    public static void main(String[] args) {
        Object lock = new Object();
        System.out.println("************加锁前**************");
        String layout0 = ClassLayout.parseInstance(lock).toPrintable();
        System.out.println(layout0);

        synchronized (lock.getClass()){
            System.out.println("************加锁时*************");
            String layout1=ClassLayout.parseInstance(lock).toPrintable();
            System.out.println(layout1);
        }

        System.out.println("***************释放锁后************");

        String layout2=ClassLayout.parseInstance(lock).toPrintable();
        System.out.println(layout2);
    }
}
