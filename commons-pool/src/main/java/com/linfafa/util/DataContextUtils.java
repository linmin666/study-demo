package com.linfafa.util;

import com.linfafa.entity.Entry;
import com.linfafa.exception.DtoException;
import org.apache.metamodel.DataContext;
import org.apache.metamodel.factory.DataContextProperties;
import org.apache.metamodel.factory.DataContextPropertiesImpl;
import org.apache.metamodel.hbase.HBaseDataContext;
import org.apache.metamodel.jdbc.JdbcDataContext;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * @author linmin
 * @since 1.0
 */
public class DataContextUtils {
    /**
     * 关闭连接,目前仅支持JDBCDataContext
     *
     * @param dataContext
     */
    public static void safeClose(DataContext dataContext) throws DtoException {
        try {
            if (dataContext == null) return;
            if (dataContext instanceof JdbcDataContext) {
                ((JdbcDataContext) dataContext).close(((JdbcDataContext) dataContext).getConnection());
            } else if (dataContext instanceof HBaseDataContext) {
                ((HBaseDataContext) dataContext).getConnection().close();
            }
        } catch (IOException e) {
            throw new DtoException("Failed to close DataContext", e);
        }
    }

    /**
     * 构建元数据配置
     *
     * @param entries
     * @return
     */
    public static DataContextProperties buildProperties(List<Entry> entries) {
        Properties properties = new Properties();
        for (Entry entry : entries) {
            //TODO 配置项检查
            String key = entry.getKey(), value = entry.getValue();
            if (key != null && value != null && !"".equals(key)) {
                properties.put(entry.getKey(), entry.getValue());
            }
        }
        return new DataContextPropertiesImpl(properties);
    }
}
