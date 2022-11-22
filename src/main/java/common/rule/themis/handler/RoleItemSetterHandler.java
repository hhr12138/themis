package common.rule.themis.handler;

import common.entity.valhalla.handler.SetterHandler;
import common.entity.valhalla.setter.Setter;
import common.rule.themis.config.AppConfiguration;
import common.rule.themis.entity.RoleData;
import common.rule.themis.entity.RoleItem;
import common.rule.themis.setter.NeedPermissionSetter;
import common.rule.themis.setter.UserPermissionSetter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class RoleItemSetterHandler extends SetterHandler<RoleData> {
    public RoleItemSetterHandler() {
        super(AppConfiguration.PSM);
    }

    @Override
    protected List<Setter<RoleData>> getSetters() {
        List<Setter<RoleData>> setters = new ArrayList<>();
        setters.add(new NeedPermissionSetter());
        setters.add(new UserPermissionSetter());
        return setters;
    }
}
