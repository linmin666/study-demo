package interpreter;

import com.linfafa.conf.DtoConf;
import com.linfafa.exception.ConnectionException;
import com.linfafa.service.DtoExecutor;
import com.linfafa.service.DtoExecutors;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ClickHouseDemo {

    public static void main(String[] args) throws IOException, ConnectionException {

        DtoConf clickhouseConf = new DtoConf()
                .setMetadataId(4);

        DtoExecutor clickhouseExecution = DtoExecutors.getDtoExecutor(clickhouseConf);
        List<Map<String, Object>> result = clickhouseExecution.execQuery("select * from super_app_realtime_dashboard limit 10");
        Iterator<Map<String, Object>> iterator = result.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> row = iterator.next();
            String id = (String) row.get("id");
            String type = (String) row.get("type");
            String userCode = (String) row.get("user_code");
            String mobile = (String) row.get("mobile");
            System.out.println("id = " + id + ",type = " + type + ",user_code = " + userCode + ", mobile = " + mobile);

        }

        List<Map<String, Object>> result1 = clickhouseExecution.execQuery("select user_code,count(id) as orders from super_app_realtime_dashboard group by user_code");
        for (Map<String, Object> map : result1) {
            map.forEach((key, value) -> {
                System.out.println(key + " = " + value);
            });
        }
        DtoExecutors.stopDtoExecutor(clickhouseExecution);


    }
}
