package com.linfafa.service;

import com.linfafa.conf.Constant;
import com.linfafa.conf.DtoConf;
import com.linfafa.exception.DtoException;
import com.linfafa.exception.DtoTimeoutException;
import com.linfafa.pool.ConnectionPool;
import com.linfafa.pool.DataContextFactory;
import com.linfafa.pool.DtoDataContextPool;
import com.linfafa.util.Pretreatment;
import org.apache.metamodel.DataContext;
import org.apache.metamodel.data.DataSet;
import org.apache.metamodel.query.Query;
import org.apache.metamodel.query.SelectClause;
import org.apache.metamodel.query.SelectItem;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author linmin
 * @since 1.0
 */
public final class DtoAnsiSqlExecutor implements DtoExecutor {
    private Logger log = org.slf4j.LoggerFactory.getLogger(DtoAnsiSqlExecutor.class);

    private ConnectionPool<DataContext> connectionPool;

    private AtomicBoolean isClosed = new AtomicBoolean(false);

    private final DtoConf dtoConf;

    private final Integer metadataId;

    protected DtoAnsiSqlExecutor(DtoConf dtoConf) {
        int metadataId = dtoConf.getMetadataId();
        Pretreatment.isValid(metadataId);
        this.dtoConf = dtoConf;
        this.connectionPool = new DtoDataContextPool(dtoConf, new DataContextFactory(metadataId));
        this.metadataId = metadataId;
    }

    @Override
    public List<Object[]> executeQuery(String sql) throws DtoException {
        DataContext dataContext = null;
        try {
            dataContext = connectionPool.getConnection();
            final DataContext dc = dataContext;
            FutureTask<List<Object[]>> future = new FutureTask<>(new Callable<List<Object[]>>() {
                @Override
                public List<Object[]> call() throws Exception {
                    Query query = dc.parseQuery(sql);
                    query.setMaxRows(dtoConf.getInt(Constant.DTO_MAX_QUERY_ROWS));
                    DataSet dataSet = dc.executeQuery(query);
                    List<Object[]> list = dataSet.toObjectArrays();
                    return list;
                }
            });
            List<Object[]> result = execute(dataContext, sql, future);
            return result;
        } catch (Exception e) {
            throw new DtoException(e);
        } finally {
            connectionPool.release(dataContext);
        }
    }


    @Override
    public List<Map<String, Object>> execQuery(String sql) throws DtoException {
        DataContext dataContext = null;
        try {
            dataContext = connectionPool.getConnection();
            final DataContext dc = dataContext;
            FutureTask<List<Map<String, Object>>> future = new FutureTask<>(new Callable<List<Map<String, Object>>>() {
                @Override
                public List<Map<String, Object>> call() throws Exception {
                    List<Map<String, Object>> list = new ArrayList<>();
                    Query query = dc.parseQuery(sql);
                    query.setMaxRows(dtoConf.getMaxQueryRows());
                    List<Object[]> resultList = dc.executeQuery(query).toObjectArrays();
                    SelectClause selectClause = query.getSelectClause();
                    for (Object[] objects : resultList) {
                        Map<String, Object> map = new HashMap<>();
                        for (int i = 0; i < selectClause.getItemCount(); i++) {
                            SelectItem item = selectClause.getItem(i);
                            String key;
                            if (item.getAlias() != null) {
                                key = item.getAlias();
                            } else if (item.hasFunction()) {
                                key = item.getAggregateFunction().getFunctionName() + "(" + item.getColumn().getName() + ")";
                            } else {
                                key = item.getColumn().getName();
                            }
                            map.put(key.toLowerCase(), objects[i]);
                        }
                        list.add(map);
                    }
                    return list;
                }
            });

            List<Map<String, Object>> result = execute(dataContext, sql, future);
            return result;
        } catch (DtoException e) {
            throw new DtoException(e);
        } finally {
            connectionPool.release(dataContext);

        }
    }

    @Override
    public void shutdown() throws DtoException{
        if (isClosed.compareAndSet(false, true)) {
            if (connectionPool != null)
                connectionPool.close();
        }
    }

    @Override
    public boolean isShutdown() {
        return isClosed.get();
    }

    private <T> T execute(final DataContext dataContext, final String sql, FutureTask<T> future) throws DtoException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            executorService.execute(future);
            T result = future.get(dtoConf.getMaxQueryWaitTime(), TimeUnit.MILLISECONDS);
            return result;
        } catch (TimeoutException e) {
            throw new DtoTimeoutException("SQL execution timeout!", e);
        } catch (InterruptedException | ExecutionException e) {
            throw new DtoException("SQL execution was interrupted or an exception occurred during SQL execution !", e);
        } finally {
            future.cancel(true);
            executorService.shutdown();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            if (!isClosed.get()) shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.finalize();
        }

    }
}
