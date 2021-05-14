package interpreter;

import com.linfafa.conf.DtoConf;
import com.linfafa.service.DtoExecutor;
import com.linfafa.service.DtoExecutors;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class HiveDemo {
    public static void main(String[] args) throws IOException {
        DtoConf hiveConf = new DtoConf()
                .setMaxQueryWaitTime(10 * 60 * 1000L)
                .setMetadataId(2);

        DtoExecutor hiveExecution = DtoExecutors.getDtoExecutor(hiveConf);
        List<Object[]> objects1 = hiveExecution.executeQuery("select * from app.brand");
        for (Object[] objects : objects1) {
            String id = (String) objects[0];
            String brand = (String) objects[1];
            System.out.println("id=" + id + ",brand=" + brand);
        }

        String sql = "select division,count(id) from app.brand group by division";
        List<Map<String, Object>> hiveResult = hiveExecution.execQuery(sql);
        for (Map<String, Object> map : hiveResult) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println("key=" + entry.getKey() + ",value=" + entry.getValue());
            }
        }
        DtoExecutors.stopDtoExecutor(hiveExecution);

    }
}
