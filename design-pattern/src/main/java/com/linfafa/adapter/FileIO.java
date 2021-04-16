package com.linfafa.adapter;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileIO {

    void readFromFile(String name) throws IOException;

    void writeToFile(String name, boolean append) throws IOException;

    void setValue(String key, String value);

    String getValue(String key);
}
