package interpreter;

import com.linfafa.conf.DtoConf;
import com.linfafa.service.DtoExecutor;
import com.linfafa.service.DtoExecutors;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MySQLDemo {
    public static void main(String[] args) throws IOException {
        DtoConf mysqlConf = new DtoConf()
                .setMetadataId(5);

        DtoExecutor mysqlDtoExecutor= DtoExecutors.getDtoExecutor(mysqlConf);
        DtoExecutors.stopDtoExecutor(mysqlDtoExecutor);
        DtoExecutor dtoExecutor=DtoExecutors.getDtoExecutor(mysqlConf);
        List<Map<String, Object>> result = dtoExecutor.execQuery("select * from person");
        for (Map<String, Object> map : result) {
            String name = (String) map.get("name");
            int age = (int) map.get("age");
            String sex = (String) map.get("sex");
            System.out.println("name = " + name + ", age = " + age + ", sex = " + sex);
        }

        DtoExecutors.stopDtoExecutor(mysqlDtoExecutor);

    }
}
