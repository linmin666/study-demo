package com.linfafa.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author linmin
 * @since 1.0
 */
public class FileUtils {
    public static Map<String, Object> getSystemProperties() {
        Properties properties;
        Map<String,Object> map=new HashMap<>();
        try {
            properties = new Properties();
            properties.load(new FileInputStream("dto.properties"));
            properties.forEach((k,v)->map.put((String) k,v));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
