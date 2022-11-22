package common.rule.themis.service.impl;

import common.entity.valhalla.util.RequestIdUtil;
import common.entity.valhalla.vo.RestResponse;
import common.rule.themis.config.AppConfiguration;
import common.rule.themis.entity.NeedPermission;
import common.rule.themis.entity.User;
import common.rule.themis.service.CommonService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommonServiceImplTest {

    @Resource
    private CommonService service;
    private RequestIdUtil requestIdUtil = new RequestIdUtil(0,1);
    @Test
    void authentication() {
    }
}