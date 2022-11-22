package common.rule.themis.controller;

import common.entity.valhalla.vo.RestResponse;
import common.rule.themis.entity.NeedPermission;
import common.rule.themis.entity.Permission;
import common.rule.themis.entity.RoleItem;
import common.rule.themis.entity.User;
import common.rule.themis.service.CommonService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CommonController {
    @Resource
    private CommonService commonService;
    @PostMapping("/authentication")
    public RestResponse<Boolean> authentication(@RequestBody RoleItem roleItem){
        return commonService.authentication(roleItem);
    }
    @PostMapping("/insert/needPermission")
    public RestResponse<Long> insertNeedPermission(@RequestParam String reqeustId, @RequestParam Long needPermission, @RequestParam List<Long> permissions){
        return commonService.addPermissionToNeedPermission(reqeustId, needPermission,permissions);
    }
}
