package com.linfafa.ch2;

/**
 * @author linmin
 * @date 2022/3/22
 *
 * 判断下述三个方法中的局部变量sb是否是线程安全的
 */
public class Demo {
    public static void main(String[] args) {

    }
    //是
    public static void m1(){
        StringBuilder sb=new StringBuilder();
        sb.append(1);
        sb.append(2);
        sb.append(3);
        System.out.println(sb.toString());
    }

    //否
    public static void m2(StringBuilder sb){
        sb.append(1);
        sb.append(2);
        sb.append(3);
        System.out.println(sb.toString());
    }

    //否
    public static StringBuilder m3(){
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        sb.append(2);
        sb.append(3);
        return sb; // 作为返回值，逃离了方法的作用范围
    }
}
