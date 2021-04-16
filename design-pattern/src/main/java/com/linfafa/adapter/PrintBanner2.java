package com.linfafa.adapter;

/**
 * 角色：Adapter
 * 第二种实现方式
 * 使用委托的方式，当调用printWeak或printStrong时，交给其他实例来（banner）处理
 */
public class PrintBanner2 extends Print2 {
    private Banner banner;

    public PrintBanner2(String str) {
        banner = new Banner(str);
    }

    public void printWeak() {
        banner.showWithParen();
    }

    public void printStrong() {
        banner.showWithAster();
    }
}
