package com.linfafa.pool;

import com.linfafa.conf.MetadataConf;
import com.linfafa.util.DataContextUtils;
import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.metamodel.DataContext;
import org.apache.metamodel.factory.DataContextFactoryRegistryImpl;
import org.apache.metamodel.factory.DataContextProperties;
import org.apache.metamodel.hbase.HBaseDataContext;
import org.apache.metamodel.jdbc.JdbcDataContext;

public class DataContextFactory extends BasePoolableObjectFactory<DataContext> {

    private final Integer metadataId;

    public DataContextFactory(Integer metadataId) {
        this.metadataId = metadataId;
    }

    @Override
    public DataContext makeObject() throws Exception {
        DataContextProperties properties = DataContextUtils
                .buildProperties(MetadataConf.getMetadata(metadataId));
        DataContext dataContext = DataContextFactoryRegistryImpl.getDefaultInstance()
                .createDataContext(properties);
        return dataContext;
    }

    @Override
    public void destroyObject(DataContext dataContext) throws Exception {
        if (dataContext instanceof JdbcDataContext) {
            ((JdbcDataContext) dataContext).close(((JdbcDataContext) dataContext).getConnection());
        } else if (dataContext instanceof HBaseDataContext) {
            ((HBaseDataContext) dataContext).getConnection().close();
        }
    }

    @Override
    public boolean validateObject(DataContext dataContext) {
        return super.validateObject(dataContext);
    }
}