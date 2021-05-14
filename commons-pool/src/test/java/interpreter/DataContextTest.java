package interpreter;

import com.linfafa.conf.MetadataConf;
import com.linfafa.util.DataContextUtils;
import org.apache.metamodel.DataContext;
import org.apache.metamodel.data.DataSet;
import org.apache.metamodel.data.Row;
import org.apache.metamodel.factory.DataContextFactoryRegistryImpl;
import org.apache.metamodel.factory.DataContextProperties;
import org.apache.metamodel.jdbc.JdbcDataContext;

import java.util.Iterator;

public class DataContextTest {
    public static void main(String[] args) {
        DataContextProperties properties = DataContextUtils
                .buildProperties(MetadataConf.getMetadata(1));
        DataContext dataContext = DataContextFactoryRegistryImpl.getDefaultInstance()
                .createDataContext(properties);
        final DataContext dc=dataContext;
        System.out.println("dataContext:"+(dataContext==dc));
        if (dataContext instanceof JdbcDataContext) {
            ((JdbcDataContext) dataContext).close(((JdbcDataContext) dataContext).getConnection());
        }
        System.out.println("Succeed to close dataContext!");
        DataSet dataSet = dc.executeQuery("select division,count(id) from brand group by division");
        Iterator<Row> iterator = dataSet.iterator();
        while (iterator.hasNext()){
            Row row = iterator.next();
            System.out.println("division:"+(int)row.getValue(0));
        }
    }
}
