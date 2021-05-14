package com.linfafa.service;

import com.linfafa.conf.Constant;
import com.linfafa.conf.DtoConf;
import com.linfafa.conf.MetadataConf;
import com.linfafa.exception.DtoException;
import com.linfafa.exception.UnsupportedExecution;
import com.linfafa.util.Pretreatment;
import org.slf4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author linmin
 * @since 1.0
 */
public class DtoExecutors {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DtoExecutors.class);

    private static ConcurrentHashMap<Integer, ? super DtoExecutor> dtoExecutors = new ConcurrentHashMap<>();

    /**
     * @param dtoConf
     * @return a {@code DtoExecution} object
     */
    public static DtoExecutor getDtoExecutor(DtoConf dtoConf) {
        Integer metadataId = dtoConf.getMetadataId();
        Pretreatment.isValid(metadataId);
        String type = MetadataConf.getType(metadataId);
        if (Constant.ANSI_SQL_TYPE.equals(type))
            return getAnsiSQLExecutor(dtoConf);
        else throw new UnsupportedExecution("Only ANSI SQL is supported currently,type=" + type + " is not supported!");
    }

    private static DtoExecutor getAnsiSQLExecutor(DtoConf dtoConf) {
        int metadataId = dtoConf.getMetadataId();
        if (dtoExecutors.containsKey(metadataId)) {
            synchronized (dtoExecutors) {
                DtoExecutor dtoExecutor = (DtoExecutor) dtoExecutors.get(metadataId);
                if (dtoExecutor.isShutdown())
                    dtoExecutors.put(metadataId, new DtoAnsiSqlExecutor(dtoConf));
            }
        } else {
            synchronized (dtoExecutors) {
                if (!dtoExecutors.containsKey(metadataId)) {
                    dtoExecutors.put(metadataId, new DtoAnsiSqlExecutor(dtoConf));
                }
            }
        }
        return (DtoExecutor) dtoExecutors.get(metadataId);
    }


    public static void stopDtoExecutor(DtoExecutor dtoExecutor) {
        try {
            if (!dtoExecutor.isShutdown())
                dtoExecutor.shutdown();
            log.info("Succeed to stop DtoExecutor !");
        } catch (DtoException e) {
            log.warn("Failed to stop DtoExecutor!");
        }
    }

}
