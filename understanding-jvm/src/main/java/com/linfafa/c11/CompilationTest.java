package com.linfafa.c11;

/**
 * -XX:+PrintCompilation参数要求虚拟机在即时编译时将被编译成本地代码的方法名称打印出来
 * （其中带%的输出说明是由回边计数器触发的栈上替换编译）
 * -XX:+PrintInlining参数要求虚拟机输出方法内联信息
 *
 * @author linmin
 * @date 2021/5/9
 */
public class CompilationTest {
    public static final int NUM = 15000;

    public static int doubleValue(int i) {
        //空循环，用于演示JIT代码优化过程
        for (int j = 0; j < 100000; ++j) ;
        return i * 2;
    }

    public static long calcSum() {
        long sum = 0;
        for (int i = 0; i < 100; ++i) {
            sum += doubleValue(i);
        }
        return sum;
    }

    public static void main(String[] args) {
        for (int i = 0; i < NUM; ++i) {
            calcSum();
        }
    }
}
