package com.linfafa.service;

import com.linfafa.exception.DtoException;
import com.linfafa.exception.DtoTimeoutException;

import java.util.List;
import java.util.Map;

/**
 * <p>The object used for executing a SQL statement
 * and returning the results it produces.
 * </p>
 *
 * @author linmin
 * @since 1.0
 */
public interface DtoExecutor{


    /**
     * Executes the given SQL statement,which returns a List of Array of {@code Object} .
     *
     * @param sql an SQL statement to be sent to the specified data source,typically a
     *            SQL {@code SELECT} statement.
     * @return
     * @throws DtoException        if the query is interrupted or an exception occurs.
     * @throws DtoTimeoutException when query execution time exceeds {@code dto.max.query.wait.time},
     *                             the current query will be canceled.
     */
    List<Object[]> executeQuery(String sql) throws DtoException;

    /**
     * Executes the given SQL statement,which returns a List of Map.
     * The key of the map is column name, and the value of the map
     * is column value.
     *
     * @param sql an SQL statement to be sent to the specified data source,typically a
     *            SQL {@code SELECT} statement.
     * @return
     * @throws DtoException        if the query is interrupted or an exception occurs.
     * @throws DtoTimeoutException when query execution time exceeds {@code dto.max.query.wait.time},
     *                             the current query will be canceled.
     */
    List<Map<String, Object>> execQuery(String sql) throws DtoException;

    void shutdown() throws DtoException;

    boolean isShutdown();

}
