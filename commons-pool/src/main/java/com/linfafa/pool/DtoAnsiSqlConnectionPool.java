package com.linfafa.pool;

import com.linfafa.conf.DtoConf;
import com.linfafa.conf.MetadataConf;
import com.linfafa.exception.DtoException;
import com.linfafa.exception.DtoTimeoutException;
import com.linfafa.util.DataContextUtils;
import org.apache.metamodel.ConnectionException;
import org.apache.metamodel.DataContext;
import org.apache.metamodel.factory.DataContextFactoryRegistryImpl;
import org.apache.metamodel.factory.DataContextProperties;
import org.apache.metamodel.factory.UnsupportedDataContextPropertiesException;
import org.slf4j.Logger;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author linmin
 * @since 1.0
 */
public class DtoAnsiSqlConnectionPool implements ConnectionPool<DataContext> {

    private Logger log = org.slf4j.LoggerFactory.getLogger(DtoAnsiSqlConnectionPool.class);

    private DtoConf dtoConf;
    /**
     * a queue stores idle connection
     */
    private LinkedBlockingQueue<DataContext> idleQueue = new LinkedBlockingQueue<>();
    /**
     * a queue stores busy connection
     */
    private LinkedBlockingQueue<DataContext> busyQueue = new LinkedBlockingQueue<>();
    /**
     * current number of active connection
     */
    private AtomicInteger activeSize = new AtomicInteger(0);
    /**
     * the flag to mark the pool is close
     */
    private AtomicBoolean isClosed = new AtomicBoolean(false);
    /**
     * all number of connection that has been created
     */
    private AtomicInteger createdCount = new AtomicInteger(0);


    public DtoAnsiSqlConnectionPool(DtoConf dtoConf) {
        this.dtoConf = dtoConf;
    }


    /**
     * The algorithm strategy is as follows:
     * <p>
     * The first step is to determine whether there is a connection in the {@code idleQueue}.
     * If there is a connection in {@code idleQueue}, return the connection and add it to
     * the {@code busyQueue}; otherwise, proceed to the next step.
     * </p>
     * <p>
     * The second step is determine whether {@code activeSize} is less than {@code maxSize}.
     * If it is less than {@code maxSize}, create a new connection, return the connection
     * and add it to {@code busyQueue}; otherwise, proceed to the next step.
     * <p>
     * The third step is to wait for the connection in the {@code busyQueue} to be released.
     * If an idle connection is acquired within the timeout period, the connection is returned
     * and added to the {@code idleQueue}; otherwise, a timeout exception is thrown.
     *
     * @return a DataContext object
     * @throws DtoException        if interrupted while waiting
     * @throws DtoTimeoutException When the time to obtain a connection
     *                             exceeds {@code dto.max.connection.wait.time}
     */
    @Override
    public DataContext getConnection() throws DtoException {
        assertOpen();
        DataContext dataContext;
        Long nowTime = System.currentTimeMillis();

        dataContext = idleQueue.poll();
        if (dataContext == null) {//(2)空闲队列idle无连接
            if (activeSize.get() < dtoConf.getMaxActiveConnection()) {//连接池中活跃连接数小于最大连接数
                if (activeSize.incrementAndGet() <= dtoConf.getMaxActiveConnection()) {//先增加连接数后判断是否小于等于maxActive
                    //创建dataContext对象
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    FutureTask<DataContext> futureTask = new FutureTask<>(() -> createDataContext());
                    try {
                        executorService.execute(futureTask);
                        Long waitTime = dtoConf.getMaxConnectionWaitTime() - (System.currentTimeMillis() - nowTime);
                        dataContext = futureTask.get(waitTime, TimeUnit.MILLISECONDS);
                        log.debug("Thread:" + Thread.currentThread().getId() + " has get a connection：" + createdCount.incrementAndGet());
                        busyQueue.offer(dataContext);
                        return dataContext;
                    } catch (InterruptedException | ExecutionException e) {
                        throw new ConnectionException(e);
                    } catch (TimeoutException e) {
                        throw new DtoTimeoutException(e);
                    } finally {
                        executorService.shutdown();
                        futureTask.cancel(true);
                    }
                } else {
                    activeSize.decrementAndGet();
                }
            }
            try {
                log.debug("Thread: " + Thread.currentThread().getId() + " is waiting for idle connection !");
                Long waitTime = dtoConf.getMaxConnectionWaitTime() - (System.currentTimeMillis() - nowTime);//剩余的等待时间
                dataContext = idleQueue.poll(waitTime, TimeUnit.MILLISECONDS);//(3)等待连接
            } catch (InterruptedException e) {
                log.warn(e.getMessage());
                throw new ConnectionException("It interrupted while waiting !", e);
            }

            if (dataContext != null) {
                log.info("Thread:" + Thread.currentThread().getId() + " has get a connection:" + createdCount.incrementAndGet());
                busyQueue.offer(dataContext);
                return dataContext;
            } else {
                log.warn("Thread:" + Thread.currentThread().getId() + " is timeout to get a connection，please retry ");
                throw new DtoTimeoutException("Thread:" + Thread.currentThread().getId() + " is timeout to get a connection，please retry ！");
            }
        }

        //(1)空闲队列有连接
        busyQueue.offer(dataContext);
        return dataContext;
    }

    @Override
    public void release(DataContext connection) throws ConnectionException {
        if (connection == null) {
            log.info("connection is null !");
            return;
        }
        if (busyQueue.remove(connection)) {
            idleQueue.offer(connection);
        } else {
            activeSize.decrementAndGet();
            throw new ConnectionException("Failed to release the connection !");
        }
    }

    @Override
    public void close() throws DtoException {
        if (isClosed.compareAndSet(false, true)) {
            idleQueue.forEach((dataContext) -> {
                DataContextUtils.safeClose(dataContext);
            });
            busyQueue.forEach((dataContext) -> {
                DataContextUtils.safeClose(dataContext);
            });
        }

    }

    /**
     * select metadata by {@code metadataId},then create an instance of {@code DataContext},
     *
     * @return an instance of {@code DataContext}
     */
    private DataContext createDataContext() throws ConnectionException {
        DataContext dataContext;
        try {
            DataContextProperties properties = DataContextUtils
                    .buildProperties(MetadataConf.getMetadata(dtoConf.getMetadataId()));
            dataContext = DataContextFactoryRegistryImpl.getDefaultInstance()
                    .createDataContext(properties);
            return dataContext;
        } catch (UnsupportedDataContextPropertiesException | ConnectionException e) {
            log.warn(e.getMessage());
            throw new ConnectionException("Failed to create the DataContext object. ", e);
        }

    }

    private void assertOpen(){
        if(isClosed.get()){
            throw new IllegalStateException("Pool is closed!");
        }
    }
}
