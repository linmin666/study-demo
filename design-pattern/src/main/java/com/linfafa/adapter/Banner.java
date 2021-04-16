package com.linfafa.adapter;

/**
 * 广告横幅
 * 角色：Adaptee
 */
public class Banner {
    private String str;

    public Banner(String str) {
        this.str = str;
    }

    /**
     * 将字符串用括号括起来
     */
    public void showWithParen() {
        System.out.println("(" + str + ")");
    }

    /**
     * 将字符串用*号括起来
     */
    public void showWithAster() {
        System.out.println("*" + str + "*");
    }
}
