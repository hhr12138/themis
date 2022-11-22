package common.rule.themis.service.impl;

import common.entity.valhalla.linked.BasicLinked;
import common.entity.valhalla.vo.RestResponse;
import common.log.scholar_of_yore.service.LogTemplate;
import common.rule.themis.config.AppConfiguration;
import common.rule.themis.entity.*;
import common.rule.themis.linked.ReadHandlerLinked;
import common.rule.themis.service.CommonService;
import common.storage.king.service.ThemisClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CommonServiceImpl implements CommonService {
    @Resource
    private ReadHandlerLinked readHandlerLinked;
    @Resource
    private LogTemplate logTemplate;
    @Resource
    private ThemisClient themisClient;
    private String psm = AppConfiguration.PSM;

    @Override
    public RestResponse<Boolean> authentication(RoleItem roleItem) {
        String requestId = roleItem.getRequestId();
        User user = roleItem.getUser();
        NeedPermission needPermission = roleItem.getNeedPermission();
        if(requestId == null || user == null || needPermission == null){
            return RestResponse.fail("targetAttribute is null, requestId="+requestId+", user="+user+", needPermission="+needPermission);
        }
        try{
            RoleData roleData = readHandlerLinked.runLinked(roleItem);
            boolean ok = true;
            List<Permission> targetPermissions = roleData.getTargetPermissions();
            Set<Permission> existPermissions = roleData.getExistPermissions();
            if(targetPermissions == null || existPermissions == null) return RestResponse.fail("targetPermission or existPermission is null: targetPermission="+targetPermissions+"existPermission="+existPermissions);
            for(Permission permission: targetPermissions){
                if(!existPermissions.contains(permission)){
                    ok = false;
                    break;
                }
            }
            return RestResponse.success(ok);
        } catch (Exception e){
            logTemplate.error(requestId, AppConfiguration.PSM,e.getMessage());
            return RestResponse.fail(e.getMessage());
        }
    }

    @Override
    public RestResponse<Long> insertNeedPermission(NeedPermission needPermission) {
        common.storage.king.entity.NeedPermission np = new common.storage.king.entity.NeedPermission();
        BeanUtils.copyProperties(needPermission,np);
        return themisClient.insertNeedPermission(np);
    }

    @Override
    public RestResponse<Long> insertRole(Role role) {
        common.storage.king.entity.Role r = new common.storage.king.entity.Role();
        BeanUtils.copyProperties(role,r);
        return themisClient.insertRole(r);
    }

    @Override
    public RestResponse<Long> insertPermission(String requestId, Permission permission) {
        common.storage.king.entity.Permission p = new common.storage.king.entity.Permission();
        BeanUtils.copyProperties(permission,p);
        return themisClient.insertPermission(p);
    }

    @Override
    public RestResponse<List<Permission>> selectPermissionByDepartmentId(String requestId, Long departmentId) {
        RestResponse<List<common.storage.king.entity.Permission>> resp = themisClient.selectPermissionByDepartment(departmentId);
        if(!resp.isSuccess()){
            logTemplate.error(requestId, psm, resp.getMsg());
        }
        List<Permission> ans = new ArrayList<>();
        List<common.storage.king.entity.Permission> data = resp.getData();
        BeanUtils.copyProperties(data,ans);
        return RestResponse.success(ans);
    }

    @Override
    public RestResponse<List<Permission>> selectPermissionByUser(String reqeustId, Long userId) {
        RestResponse<List<common.storage.king.entity.Permission>> response = themisClient.selectPermissionByUser(userId);
        if(!response.isSuccess()) {
            logTemplate.error(reqeustId,psm,response.getMsg());
        }
        List<common.storage.king.entity.Permission> data = response.getData();
        List<Permission> ans = new ArrayList<>();
        BeanUtils.copyProperties(data,ans);
        return RestResponse.success(ans);
    }

    @Override
    public RestResponse<List<Permission>> selectPermissionByCompany(String reqeustId, Long companyId) {
        RestResponse<List<common.storage.king.entity.Permission>> response = themisClient.selectPermissionByCompany(companyId);
        if(!response.isSuccess()) {
            logTemplate.error(reqeustId,psm,response.getMsg());
        }
        List<common.storage.king.entity.Permission> data = response.getData();
        List<Permission> ans = new ArrayList<>();
        BeanUtils.copyProperties(data,ans);
        return RestResponse.success(ans);
    }

    @Override
    public RestResponse<Long> addRolesToUser(String reqeustId, Long userId, List<Long> roleIds) {
        return themisClient.addRolesToUser(userId,roleIds);
    }

    @Override
    public RestResponse<Long> addRolesToCompany(String reqeustId, Long companyId, List<Long> roleIds) {
        return themisClient.addRolesToCompany(companyId,roleIds);
    }

    @Override
    public RestResponse<Long> addRolesToDepartment(String reqeustId, Long departmentId, List<Long> roleIds) {
        return themisClient.addRolesToDepartment(departmentId,roleIds);
    }

    @Override
    public RestResponse<Long> addPermissionToRole(String reqeustId, Long roleId, List<Long> permissionIds) {
        return themisClient.addPermissionToRole(roleId,permissionIds);
    }

    @Override
    public RestResponse<Long> addPermissionToUser(String reqeustId, Long userId, List<Long> permissionIds) {
        return themisClient.addPermissionToUser(userId,permissionIds);
    }

    @Override
    public RestResponse<Long> addPermissionToNeedPermission(String reqeustId, Long needPermission, List<Long> permissions) {
        RestResponse<Long> longRestResponse = themisClient.addPermissionToNeedPermission(needPermission, permissions);
        return longRestResponse;
    }
}
