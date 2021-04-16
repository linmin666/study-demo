package com.linfafa.templatemethod;

public class Main {
    public static void main(String[] args) {
        AbstractDisplay charDisplay= new CharDisplay('H');
        AbstractDisplay stringDisplay = new StringDisplay("hello world");
        charDisplay.display();
        stringDisplay.display();

    }
}
