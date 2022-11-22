package common.rule.themis.service;
import common.entity.valhalla.vo.RestResponse;
import common.rule.themis.entity.NeedPermission;
import common.rule.themis.entity.Permission;
import common.rule.themis.entity.Role;
import common.rule.themis.entity.RoleItem;

import java.util.List;

public interface CommonService {
    /**
     * 继承的验证方法, 判断当前对象是否具有调用当前方法/字段的权限
     * @param roleItem: requestId, user, needPermission
     * @return
     */
    RestResponse<Boolean> authentication(RoleItem roleItem);

    /**
     * @return 主键
     */
    RestResponse<Long> insertNeedPermission(NeedPermission needPermission);
    RestResponse<Long> insertRole(Role role);
    RestResponse<Long> insertPermission(String requestId, Permission permission);

    RestResponse<List<Permission>> selectPermissionByDepartmentId(String requestId, Long departmentId);
    RestResponse<List<Permission>> selectPermissionByUser(String reqeustId, Long userId);
    RestResponse<List<Permission>> selectPermissionByCompany(String requestId, Long companyId);

    /**
     * 给用户添加角色
     * @param userId
     * @param roleIds
     * @return 添加成功个数
     */
    RestResponse<Long> addRolesToUser(String requestId, Long userId, List<Long> roleIds);

    /**
     * 给公司添加基础角色
     * @param companyId
     * @param roleIds
     * @return
     */
    RestResponse<Long> addRolesToCompany(String reqeustId, Long companyId, List<Long> roleIds);

    /**
     * 给部门添加基础角色
     * @param departmentId
     * @param roleIds
     * @return
     */
    RestResponse<Long> addRolesToDepartment(String requestId, Long departmentId, List<Long> roleIds);
    /**
     * 给角色添加权限
     * @return 添加成功个数
     */
    RestResponse<Long> addPermissionToRole(String reqeustId, Long roleId, List<Long> permissionIds);

    /**
     * 给用户添加权限
     * @param userId
     * @param permissionIds
     * @return
     */
    RestResponse<Long> addPermissionToUser(String reqeustId, Long userId, List<Long> permissionIds);

    /**
     * 给权限需要对象添加所需权限
     * @param needPermission
     * @param permissions
     * @return
     */
    RestResponse<Long> addPermissionToNeedPermission(String reqeustId, Long needPermission, List<Long> permissions);


}
