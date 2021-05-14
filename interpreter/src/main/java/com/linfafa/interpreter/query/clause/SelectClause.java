package com.linfafa.interpreter.query.clause;

import com.linfafa.interpreter.query.Query;
import com.linfafa.interpreter.query.item.SelectItem;

/**
 * @author linmin
 * @date 2021/5/7
 */
public class SelectClause extends BasedQueryClause<SelectItem> {

    public SelectClause(Query query, String prefix, String delim) {
        super(query, prefix, delim);
    }

}
