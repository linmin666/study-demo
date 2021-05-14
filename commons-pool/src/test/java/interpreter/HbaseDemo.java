package interpreter;

import com.linfafa.conf.DtoConf;
import com.linfafa.service.DtoExecutor;
import com.linfafa.service.DtoExecutors;
import com.linfafa.util.HBaseUtils;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.List;
import java.util.Map;

public class HbaseDemo {

    public static void main(String[] args) {

        DtoConf hbaseConf = new DtoConf()
                .setMetadataId(3);

        DtoExecutor hbaseExecution = DtoExecutors.getDtoExecutor(hbaseConf);
        List<Object[]> result = hbaseExecution.executeQuery("select _id,f1,f2 from t1");

        for (Object[] row : result) {
            String rowKey = HBaseUtils.getRowKey(row[0]);
            System.out.println("rowKey: " + rowKey);
            System.out.println("f1:");
            Map<String, byte[]> f1 = HBaseUtils.getColumns(row[1]);
            f1.forEach((k, v) -> {
                System.out.println(k + " = " + Bytes.toString(v));
            });
        }
        DtoExecutors.stopDtoExecutor(hbaseExecution);

    }
}
