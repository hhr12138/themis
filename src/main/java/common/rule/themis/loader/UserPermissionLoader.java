package common.rule.themis.loader;

import common.entity.valhalla.loader.Impl.AbstractLoader;
import common.entity.valhalla.vo.PreHookResp;
import common.entity.valhalla.vo.RestResponse;
import common.log.scholar_of_yore.service.LogTemplate;
import common.rule.themis.entity.Permission;
import common.rule.themis.entity.RoleItem;
import common.storage.king.entity.User;
import common.storage.king.service.ThemisClient;
import org.springframework.beans.BeanUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UserPermissionLoader extends AbstractLoader {
    public static final String USER_PERMISSION_LOADER = "userPermissionLoader";
    private LogTemplate logTemplate;
    private ThemisClient themisClient;
    private RoleItem roleItem;
    private List<common.storage.king.entity.Permission> permissions = new ArrayList<>();

    public UserPermissionLoader(RoleItem roleItem, ConcurrentHashMap<String, Object> map, LogTemplate logTemplate, ThemisClient themisClient, String... preLoader) {
        super(map, USER_PERMISSION_LOADER, preLoader);
        this.roleItem = roleItem;
        this.logTemplate = logTemplate;
        this.themisClient = themisClient;
    }

    @Override
    public PreHookResp preHook() {
        if(roleItem.getUser() == null || roleItem.getUser().getId() == null || roleItem.getUser().getId() == 0) {
            return PreHookResp.fail();
        }
        return PreHookResp.success();
    }

    @Override
    public Exception loadData() {
        try{
            Long userId = roleItem.getUser().getId();
            User user = new User();
            user.setId(userId);
            RestResponse<List<common.storage.king.entity.Permission>> response = themisClient.getUserPermissions(user);
            if(!response.isSuccess()) throw new Exception(response.getMsg());
            this.permissions = response.getData();
        } catch (Exception e){
            return e;
        }
        return null;
    }

    @Override
    public Exception postHook() {
        List<Permission> permissions = new ArrayList<>();
        BeanUtils.copyProperties(this.permissions,permissions);
        Set<Permission> permissionSet = new HashSet<>();
        ConcurrentHashMap<String, Object> map = this.getMap();
        map.put(this.getName(),permissionSet);
        return null;
    }

}
