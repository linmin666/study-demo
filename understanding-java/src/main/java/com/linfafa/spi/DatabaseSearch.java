package com.linfafa.spi;

import java.util.List;

/**
 * @author linmin
 * @date 2021/7/3
 */
public class DatabaseSearch implements Search {
    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("Database Search " + keyword);
        return null;
    }
}
