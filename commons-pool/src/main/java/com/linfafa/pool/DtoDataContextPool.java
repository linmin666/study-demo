package com.linfafa.pool;

import com.linfafa.conf.DtoConf;
import com.linfafa.exception.DtoException;
import com.linfafa.util.DataContextUtils;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.metamodel.DataContext;
import org.slf4j.Logger;


public class DtoDataContextPool implements ConnectionPool<DataContext> {

    private Logger log = org.slf4j.LoggerFactory.getLogger(DtoDataContextPool.class);

    private DtoConf dtoConf;

    private GenericObjectPool<DataContext> pool;

    private DataContextFactory factory;

    public DtoDataContextPool(DtoConf dtoConf, DataContextFactory factory) {
        this.dtoConf = dtoConf;
        this.factory = factory;
        this.pool = new GenericObjectPool<>(factory);
        init();
    }

    private void init() {
        pool.setMaxIdle(dtoConf.getMaxIdleConnection());
        pool.setMinIdle(dtoConf.getMinIdleConnection());
        pool.setMaxActive(dtoConf.getMaxActiveConnection());
        pool.setMaxWait(dtoConf.getMaxConnectionWaitTime());
        pool.setTimeBetweenEvictionRunsMillis(dtoConf.getTimeBetweenEvictionRuns());
        pool.setNumTestsPerEvictionRun(dtoConf.getNumTestsPerEvictionRun());
        pool.setMinEvictableIdleTimeMillis(dtoConf.getMinEvictableIdleTime());
        pool.setSoftMinEvictableIdleTimeMillis(dtoConf.setSoftMinEvictableIdleTime());
    }

    @Override
    public DataContext getConnection() throws DtoException {
        try {
            return pool.borrowObject();
        } catch (Exception e) {
            throw new DtoException("Failed to obtain a instance of DataContext.", e);
        }
    }

    @Override
    public void release(DataContext dataContext) throws DtoException {
        try {
            pool.returnObject(dataContext);
        } catch (Exception e) {
            log.info("Failed to release the instance of DataContext.",e);
            DataContextUtils.safeClose(dataContext);
        }
    }

    @Override
    public void close() throws DtoException {
        try {
            pool.close();
        } catch (Exception e) {
            throw new DtoException("Failed to close the object pool of DataContext.");
        }

    }

}
