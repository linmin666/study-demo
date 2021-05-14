package com.linfafa.conf;

import com.alibaba.fastjson.JSON;
import com.linfafa.entity.Entry;
import com.linfafa.entity.Metadata;
import com.linfafa.exception.UnsupportedMetaException;
import com.linfafa.util.Pretreatment;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author linmin
 * @since 1.0
 */
public class MetadataConf {

    private static final Logger log = LoggerFactory.getLogger(MetadataConf.class);
    /**
     * A map stores metadata,key is the id of metadata，value is an instance of {@code Metadata}.
     */
    public static Map<Integer, Metadata> METADATA_MAP;

    static {
        try {
            METADATA_MAP = MetadataConf.loadMetadata(Constant.METADATA_CONFIG_DIR);
        } catch (FileNotFoundException e) {
            log.error("Failed to initialize the metadata of DTO,please check you metadata directory!", e);
            e.printStackTrace();
        }
    }

    /**
     * Load metadata and store metadata in a map, where
     * the key is the unique primary key ID of the metadata
     * and the value is the metadata.
     *
     * @param path The Directory where the metadata files are stored.
     * @throws FileNotFoundException    when the file path is not found.
     * @throws UnsupportedMetaException if metadata is wrong
     */
    public static Map<Integer, Metadata> loadMetadata(String path) throws FileNotFoundException {
        File fileDir = new File(path);
        Map<Integer, Metadata> map = new HashMap<>();
        try {
            if (!fileDir.exists())
                throw new FileNotFoundException("The Metadata Directory " + path + " is not found !");
            File[] files = fileDir.listFiles((dir, name) -> name.contains("json"));
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String str = FileUtils.readFileToString(file, "UTF-8");
                        Metadata metadata = JSON.parseObject(str, Metadata.class);
                        Pretreatment.isValid(metadata);
                        map.put(metadata.getId(), metadata);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String getName(int id) {
        Pretreatment.isValid(id);
        return METADATA_MAP.get(id).getName();
    }

    public static String getType(int id) {
        Pretreatment.isValid(id);
        return METADATA_MAP.get(id).getType();
    }

    public static List<Entry> getMetadata(int id) {
        Pretreatment.isValid(id);
        return METADATA_MAP.get(id).getMetadata();
    }
}
