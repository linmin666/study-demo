package com.linfafa.doublepointer;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author linmin
 * @date 2021/9/19
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InstantiationException {
        String path="/Users/linmin/Download/jars/";
        URLClassLoader loader_1 =
                new URLClassLoader(new URL[]{new File(path).toURI().toURL()});
        MyClassLoader loader_2 =
                new MyClassLoader(path);

        Class<?> test1 = loader_1.loadClass("Test");
        test1.newInstance();
        Class<?> test2 = loader_2.loadClass("Test");
        test2.newInstance();


    }

}
