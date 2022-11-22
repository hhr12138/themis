package common.rule.themis.loader;

import common.entity.valhalla.loader.Impl.AbstractLoader;
import common.entity.valhalla.vo.PreHookResp;
import common.entity.valhalla.vo.RestResponse;
import common.log.scholar_of_yore.service.LogTemplate;
import common.rule.themis.entity.NeedPermission;
import common.rule.themis.entity.Permission;
import common.rule.themis.entity.RoleItem;
import common.storage.king.service.ThemisClient;
import org.springframework.beans.BeanUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

//这玩意属于基础物料, 无论如何必须加载那种
public class NeedPermissionLoader extends AbstractLoader {
    public static final String NEED_PERMISSION_LOADER = "needPermissionLoader";
    private LogTemplate logTemplate;
    private RoleItem roleItem;
    private ThemisClient themisClient;
    private List<common.storage.king.entity.Permission> permissions;

    public NeedPermissionLoader(RoleItem roleItem, ConcurrentHashMap<String, Object> map, LogTemplate logTemplate, ThemisClient themisClient, String... preLoader) {
        super(map, NEED_PERMISSION_LOADER, preLoader);
        this.roleItem = roleItem;
        this.logTemplate = logTemplate;
        this.themisClient = themisClient;
    }

    @Override
    public PreHookResp preHook() {
        NeedPermission needPermission = roleItem.getNeedPermission();
        if (needPermission == null) {
            return PreHookResp.fail();
        }
        return PreHookResp.success();
    }

    @Override
    public Exception loadData() {
        this.startTime = System.currentTimeMillis();
        try{
            NeedPermission needPermission = this.roleItem.getNeedPermission();
            common.storage.king.entity.NeedPermission np = new common.storage.king.entity.NeedPermission();
            BeanUtils.copyProperties(needPermission,np);
            RestResponse<List<common.storage.king.entity.Permission>> response = themisClient.getPermissions(np);
            if(!response.isSuccess()){
                throw new Exception((response.getMsg()));
            }
            this.permissions = response.getData();
        } catch (Exception e){
            return e;
        } finally {
            this.endTime = System.currentTimeMillis();
        }
        return null;
    }

    @Override
    public Exception postHook() {
        ConcurrentHashMap<String, Object> map = this.getMap();
        List<Permission> permissions = new ArrayList<>();
        BeanUtils.copyProperties(this.permissions,permissions);
        map.put(this.getName(),permissions);
        return null;
    }
}
