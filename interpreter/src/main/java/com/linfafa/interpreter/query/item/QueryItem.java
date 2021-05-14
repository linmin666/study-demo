package com.linfafa.interpreter.query.item;

import com.linfafa.interpreter.query.Query;

/**
 * @author linmin
 * @date 2021/5/7
 */
public interface QueryItem {
    String toSql();

    Query getQuery();

    void setQuery(Query query);
}
