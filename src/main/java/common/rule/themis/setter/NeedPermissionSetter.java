package common.rule.themis.setter;

import common.entity.valhalla.setter.Setter;
import common.rule.themis.entity.Permission;
import common.rule.themis.entity.RoleData;
import common.rule.themis.entity.RoleItem;
import common.rule.themis.loader.NeedPermissionLoader;

import java.util.List;
import java.util.Map;

public class NeedPermissionSetter implements Setter<RoleData> {

    @Override
    public void setToItem(Map<String, Object> attributes, RoleData item) {
        if(!attributes.containsKey(NeedPermissionLoader.NEED_PERMISSION_LOADER)){
            return;
        }
        List<Permission> permissions = (List<Permission>)attributes.get(NeedPermissionLoader.NEED_PERMISSION_LOADER);
        item.setTargetPermissions(permissions);
    }
}
