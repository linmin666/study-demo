package com.linfafa.interpreter.query.clause;

import com.linfafa.interpreter.query.Query;
import com.linfafa.interpreter.query.item.QueryItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linmin
 * @date 2021/5/7
 */
public class BasedQueryClause<E extends QueryItem> implements QueryClause<E> {

    public static final String PREFIX_SELECT = "SELECT ";
    public static final String PREFIX_FROM = " FROM ";
    public static final String PREFIX_WHERE = " WHERE ";
    public static final String PREFIX_GROUP_BY = " GROUP BY ";
    public static final String PREFIX_HAVING = " HAVING ";
    public static final String PREFIX_ORDER_BY = " ORDER BY ";
    public static final String DELIM_COMMA = ", ";
    public static final String DELIM_AND = " AND ";

    private final Query _query;
    private final List<E> _items = new ArrayList<>();
    private final String _prefix;
    private final String _delim;

    public BasedQueryClause(Query query, String prefix, String delim) {
        _query = query;
        _prefix = prefix;
        _delim = delim;
    }

    @Override
    public QueryClause<E> setItems(E... items) {
        _items.clear();
        return addItems(items);
    }

    @Override
    public QueryClause<E> addItems(E... items) {
        for (E item : items) {
            addItem(item);
        }
        return this;
    }

    @Override
    public QueryClause<E> addItem(E item) {
        if(item.getQuery()==null){
            item.setQuery(_query);
        }
        _items.add(item);
        return this;
    }

    @Override
    public QueryClause<E> removeItems() {
        _items.clear();
        return this;
    }

    @Override
    public QueryClause<E> removeItems(E item) {
        _items.remove(item);
        return this;
    }

    @Override
    public E getItem(int index) {
        return _items.get(index);
    }

    @Override
    public String toSql() {
        return null;
    }
}
