package com.linfafa.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author linmin
 * @date 2021/7/3
 */
public class Test {
    public static void main(String[] args) {
        ServiceLoader<Search> serviceLoader = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Search search = iterator.next();
            search.searchDoc("hello");
        }
    }
}
