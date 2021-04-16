package com.linfafa.adapter;

/**
 * 角色：Adapter
 * 第一种实现方式，使用继承
 * 适配器角色，适配已有程序Banner实现新需求Print
 */
public class PrintBanner extends Banner implements Print {
    public PrintBanner(String str) {
        super(str);
    }

    public void printWeak() {
        showWithParen();
    }

    public void printStrong() {
        showWithAster();
    }
}
