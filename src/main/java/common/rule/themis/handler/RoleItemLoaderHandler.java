package common.rule.themis.handler;

import common.entity.valhalla.handler.LoaderHandler;
import common.entity.valhalla.loader.BasicLoader;
import common.log.scholar_of_yore.service.LogTemplate;
import common.rule.themis.config.AppConfiguration;
import common.rule.themis.entity.NeedPermission;
import common.rule.themis.entity.RoleItem;
import common.rule.themis.loader.NeedPermissionLoader;
import common.rule.themis.loader.UserPermissionLoader;
import common.storage.king.service.ThemisClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class RoleItemLoaderHandler extends LoaderHandler<RoleItem> {
    @Resource
    private LogTemplate logTemplate;
    @Resource
    private ThemisClient themisClient;

    public RoleItemLoaderHandler() {
        super(AppConfiguration.PSM);
    }

    @Override
    protected List<BasicLoader> getAllLoaders(ConcurrentHashMap<String, Object> map, RoleItem item) {
        List<BasicLoader> loaders = new ArrayList<>();
        loaders.add(new NeedPermissionLoader(item,map,logTemplate,themisClient));
        loaders.add(new UserPermissionLoader(item,map,logTemplate,themisClient));
        return loaders;
    }
}
