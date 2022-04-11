package com.linfafa.bean;

/**
 * @author linmin
 * @date 2022/3/28
 */
public class Student {

    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
