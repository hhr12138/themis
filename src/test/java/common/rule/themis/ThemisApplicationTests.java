package common.rule.themis;

import common.log.scholar_of_yore.service.CommonLogClient;
import common.rule.themis.entity.Permission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ThemisApplicationTests {

    @Test
    void contextLoads() {
        List<Permission> list = new ArrayList<>();
        common.storage.king.entity.Permission permission = new common.storage.king.entity.Permission();
        permission.setCompanyId(1L);
        permission.setId(1L);
        permission.setDepartmentId(-1L);
        permission.setPermission("test");
        List<common.storage.king.entity.Permission> permissions = Arrays.asList(permission);
        BeanUtils.copyProperties(permission,list);
        System.out.println(list.get(0).getClass());
    }

}
