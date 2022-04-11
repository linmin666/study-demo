package com.linfafa.collection.list;

import com.linfafa.bean.Student;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author linmin
 * @date 2022/3/28
 */
public class FailFastVsFailSafe {
    // fail-fast 一旦发现遍历的同时其他人来修改，则立即抛异常
    // fail-safe 发现遍历的时候其他人来修改，应当能有应对策略，例如牺牲一致性来让整个遍历运行完成

    private static void failFast(){
        ArrayList<Student> list=new ArrayList<>();
        list.add(new Student("A"));
        list.add(new Student("B"));
        list.add(new Student("C"));
        list.add(new Student("D"));

        for(Student student:list){
            System.out.println(student);//在遍历时debug，模拟另一个线程为ArrayList添加一个元素，则遍历报错
            // 1. debug断点添加Condition：student.name.equals("C")
            // 2. 为list添加Evaluate Expression：list.add(new Student("E"))
            // 3. 继续执行，当遍历完C后，会抛出ConcurrentModificationException
        }

        System.out.println(list);
    }

    private static void failSafe(){
        CopyOnWriteArrayList<Student> list = new CopyOnWriteArrayList<>();
        list.add(new Student("A"));
        list.add(new Student("B"));
        list.add(new Student("C"));
        list.add(new Student("D"));

        for(Student student:list){
            System.out.println(student);
        }

        System.out.println(list);
    }
    public static void main(String[] args) {
        failFast();
    }
}
