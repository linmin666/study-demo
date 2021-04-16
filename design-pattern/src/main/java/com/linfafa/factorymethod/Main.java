package com.linfafa.factorymethod;

public class Main {
    public static void main(String[] args) {
        Factory factory = new IDCardFactory();
        Product jack = factory.create("Jack");
        Product rose = factory.create("Rose");
        jack.use();
        rose.use();
    }
}
