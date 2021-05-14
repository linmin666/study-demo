package com.linfafa.util;

import com.linfafa.conf.MetadataConf;
import com.linfafa.entity.Metadata;
import com.linfafa.exception.UnsupportedMetaException;

import java.util.NoSuchElementException;

/**
 * @author linmin
 * @since 1.0
 */
public class Pretreatment {
    public static void isValid(Integer id) {
        if (id == null) throw new NullPointerException("The metadataId can not be null !");
        if (!MetadataConf.METADATA_MAP.containsKey(id))
            throw new NoSuchElementException("The metadataId is invalid !");
    }

    //TODO 校验元数据配置项的正确性
    public static void isValid(Metadata metadata) throws UnsupportedMetaException {

    }

}
