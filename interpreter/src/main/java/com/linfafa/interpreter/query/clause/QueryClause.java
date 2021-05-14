package com.linfafa.interpreter.query.clause;

/**
 * @author linmin
 * @date 2021/5/7
 */
public interface QueryClause<E> {
    /**
     * 清空其他items，并添加当前items
     */
    QueryClause<E> setItems(E... items);

    QueryClause<E> addItems(E... items);

    QueryClause<E> addItem(E item);

    QueryClause<E> removeItems();

    QueryClause<E> removeItems(E item);

    E getItem(int index);

    String toSql();


}
