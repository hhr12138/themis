package common.rule.themis;

import common.log.scholar_of_yore.service.CommonLogClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"common.storage.king.service","common.log.scholar_of_yore.service"})
public class ThemisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThemisApplication.class, args);
    }

}
