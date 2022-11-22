package common.rule.themis.config;

import common.entity.valhalla.util.RequestIdUtil;
import common.log.scholar_of_yore.service.LogTemplate;
import common.log.scholar_of_yore.service.impl.CommonLogTemplateImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "common.rule.themis")
@Configuration
public class AppConfiguration {
    public static String PSM = "common.rule.themis";
    private Long workId;
    private Long datacenterId;
    @Bean
    public LogTemplate logTemplate(){
        return new CommonLogTemplateImpl();
    }
}
