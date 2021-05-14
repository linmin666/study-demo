package com.linfafa.pool;

import com.linfafa.exception.DtoException;

import java.io.Closeable;

/**
 * @param <T> the type of connections in this connection pool.
 * @author linmin
 * @since 1.0
 */
public interface ConnectionPool<T> extends Closeable {

    /**
     * Obtains an instance from this pool.By contract,client
     * must return the instance using {@link #release(Object)},
     * or a related method as defined in an implementation or
     * sub-interface.
     *
     * @return an {@code DataContext} object
     * @throws DtoException        if interrupted while waiting
     * @throws DtoTimeoutException When the time to obtain a connection
     *                             exceeds {@code dto.max.connection.wait.time}
     */
    T getConnection() throws DtoException;

    /**
     * Return an instance to the pool. By contract,{@code connection}
     * must have been obtained using {@link #getConnection()} or a related
     * method as defined in an implementation or sub-interface.
     *
     * @param connection a {@link #getConnection() obtained} instance to
     *                   be returned.
     * @throws DtoException if an instance cannot be returned to the pool
     */
    void release(T connection) throws DtoException;

    /**
     * Closes this pool,and free any resources associated with it.
     *
     * @throws DtoException
     */
    void close() throws DtoException;

}
