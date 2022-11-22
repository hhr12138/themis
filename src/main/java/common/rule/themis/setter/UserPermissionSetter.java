package common.rule.themis.setter;

import common.entity.valhalla.setter.Setter;
import common.rule.themis.entity.Permission;
import common.rule.themis.entity.RoleData;
import common.rule.themis.entity.RoleItem;
import common.rule.themis.loader.UserPermissionLoader;

import java.util.Map;
import java.util.Set;

public class UserPermissionSetter implements Setter<RoleData> {
    @Override
    public void setToItem(Map<String, Object> attributes, RoleData item) {
        if(!attributes.containsKey(UserPermissionLoader.USER_PERMISSION_LOADER)) return;
        Set<Permission> permissions = (Set<Permission>)attributes.get(UserPermissionLoader.USER_PERMISSION_LOADER);
        item.setExistPermissions(permissions);
    }
}
