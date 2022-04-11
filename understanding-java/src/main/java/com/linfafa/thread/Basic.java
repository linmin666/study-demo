package com.linfafa.thread;

/**
 * @author linmin
 * @date 2021/9/20
 */
public class Basic {
    //ThreadLocal<T> 线程本地的变量
    public static ThreadLocal<Long> threadLocal=new ThreadLocal(){

        //延迟加载
        @Override
        protected Object initialValue() {
            System.out.println("Initial Value Run");
            return Thread.currentThread().getId();
        }
    };

    public static void main(String[] args) {
//        threadLocal.set(101l);
        new Thread(){
            @Override
            public void run() {
                System.out.println(threadLocal.get());
            }
        }.start();
        threadLocal.set(107l);
        threadLocal.remove();
        System.out.println(threadLocal.get());
    }


    /**
     * 四种应用场景
     * 1. 线程资源持有
     *  持有线程资源供线程的各个部分使用，全局获取，减少编程难度
     *
     * 2. 线程资源一致性
     *   帮助需要保持线程一致的资源（如数据库事务）维护一致性，降低编程难度；
     *
     * 3. 线程安全
     *  帮助只考虑单线程的程序库，无缝向多线程场景迁移；
     *
     * 4. 分布式计算
     *   帮助分布式计算场景的各个线程累计局部计算结果；
     */
}
