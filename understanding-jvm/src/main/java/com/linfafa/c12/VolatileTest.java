package com.linfafa.c12;

/**
 * 1. 关于volatile的可见性
 * 当一个变量被定义次是volatile之后，它具备的第一项特性是 保证此变量对所有线程的可见性。
 * 🌟🌟🌟 Java中的运算操作并非原子操作，这导致volatile变量的运算在并发下一样是不安全的。
 * 如下代码
 * 我们期待的结果是20*1000，但最后运行的结果可能是小于它的。
 * 这是因为race++操作不是一个原子操作。
 */
public class VolatileTest {
    public static volatile int race = 0;

    public static void increase() {
        race++; //非原子操作
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
            System.out.println("Thread" + i + "is started!");
        }
        //等待所有线程运行结束
        while (Thread.activeCount() > 2)
            System.out.println("ActiveCount is " + Thread.activeCount());
        Thread.yield();

        System.out.println(race);
    }
}
