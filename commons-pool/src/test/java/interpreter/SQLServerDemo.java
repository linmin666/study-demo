package interpreter;

import com.linfafa.conf.DtoConf;
import com.linfafa.service.DtoExecutor;
import com.linfafa.service.DtoExecutors;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SQLServerDemo {

    public static void main(String[] args) throws IOException {
        DtoConf hiveConf = new DtoConf()
                .setMetadataId(1)
                .setMaxQueryWaitTime(100000L)
                .setMaxConnectionWaitTime(3000L)
                .setMaxActiveConnection(100);

        DtoExecutor hiveExecution = DtoExecutors.getDtoExecutor(hiveConf);
        List<Object[]> objects = hiveExecution.executeQuery("select division,count(id) from brand group by division");
        for (Object[] object : objects) {
            String division = (String) object[0];
            int count = (int) object[1];
            System.out.println("division=" + division + ", count=" + count);
        }
        List<Map<String, Object>> hiveResult = hiveExecution.execQuery("select division,count(id) from brand group by division");

        for (Map<String, Object> map : hiveResult) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println("key=" + entry.getKey() + ",value=" + entry.getValue());
            }
        }

        DtoExecutors.stopDtoExecutor(hiveExecution);

    }
}
