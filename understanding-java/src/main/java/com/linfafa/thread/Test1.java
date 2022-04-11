package com.linfafa.thread;

/**
 * @author linmin
 * @date 2021/9/20
 */

public class Test1  {
    static ThreadLocal<Integer> count=new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return 0;
        }
    };

    int read(){
        return count.get();
    }

    void write(){
        count.set(count.get()+1);
    }

    public static void main(String[] args) {

    }

}
