package com.linfafa.templatemethod;

public class CharDisplay extends AbstractDisplay{
    private char ch;

    public CharDisplay(char ch){
        this.ch=ch;
    }
    /**
     * 显示字符串<<
     */
    public void open() {
        System.out.print("<<");
    }

    /**
     * 显示构造函数接收的一个字符
     */
    public void print() {
        System.out.print(ch);
    }
    /**
     * 显示字符串>>
     */
    public void close() {
        System.out.println(">>");
    }
}
