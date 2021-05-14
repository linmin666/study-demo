package com.linfafa.util;

import org.apache.hadoop.hbase.util.Bytes;
import org.apache.metamodel.hbase.HBaseFamilyMap;

import java.util.HashMap;
import java.util.Map;

public class HBaseUtils {
    public static String getRowKey(Object obj) {
        return Bytes.toString((byte[]) obj);
    }

    public static Map<String, byte[]> getColumns(Object obj) {
        HBaseFamilyMap map = (HBaseFamilyMap) obj;
        Map<String, byte[]> res = new HashMap<>();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            String key = Bytes.toString((byte[]) entry.getKey());
            byte[] value = (byte[]) entry.getValue();
            res.put(key, value);
        }
        return res;
    }

}
