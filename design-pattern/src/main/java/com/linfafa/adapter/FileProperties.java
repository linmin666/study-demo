package com.linfafa.adapter;

import java.io.*;
import java.util.Properties;

/**
 *  练习一
 *  使用Adapter模式将一个属性集合保存到文件中的FileProperties
 */
public class FileProperties implements FileIO {
    private Properties properties;

    public FileProperties() {
        properties = new Properties();
    }

    public void readFromFile(String name) throws IOException {
        properties.load(new FileInputStream(name));
    }

    public void writeToFile(String name, boolean append) throws IOException {
        properties.list(new PrintStream(new FileOutputStream(name), append));
    }

    public void setValue(String key, String value) {
        properties.setProperty(key, value);
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
