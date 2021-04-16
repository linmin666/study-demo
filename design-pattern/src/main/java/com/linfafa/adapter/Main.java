package com.linfafa.adapter;

import java.io.IOException;

/**
 * 角色：Client
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Print print = new PrintBanner("hello");
        print.printStrong();
        print.printWeak();

        FileIO fileIO = new FileProperties();
        fileIO.readFromFile("design-pattern/src/main/resources/input.txt");
        String year = fileIO.getValue("year");
        System.out.println(year);

        fileIO.setValue("year","2021");
        fileIO.setValue("month","04");
        fileIO.setValue("date","16");
        fileIO.writeToFile("design-pattern/src/main/resources/output.txt",true);

    }
}
