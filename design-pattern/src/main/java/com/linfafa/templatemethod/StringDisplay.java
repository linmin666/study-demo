package com.linfafa.templatemethod;

/**
 * open    +-----------+
 * print   ｜ hello,lm |
 * close   +-----------+
 */
public class StringDisplay extends AbstractDisplay {
    private String str;
    private int width;

    public StringDisplay(String str) {
        this.str = str;
        this.width = str.getBytes().length;
    }

    public void open() {
        printLen();
    }

    public void print() {
        System.out.println("| " + str + " |");
    }

    public void close() {
       printLen();
    }

    private void printLen() {
        System.out.print("+");
        for (int i = 0; i < width+2; i++) {
            System.out.print("-");
        }
        System.out.println("+");

    }
}
