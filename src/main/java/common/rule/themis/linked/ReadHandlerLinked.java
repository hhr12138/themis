package common.rule.themis.linked;

import common.entity.valhalla.bo.LoaderHandlerResp;
import common.entity.valhalla.handler.LoaderHandler;
import common.entity.valhalla.handler.SetterHandler;
import common.entity.valhalla.linked.BasicLinked;
import common.log.scholar_of_yore.service.LogTemplate;
import common.rule.themis.config.AppConfiguration;
import common.rule.themis.entity.RoleData;
import common.rule.themis.entity.RoleItem;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
@Component
public class ReadHandlerLinked implements BasicLinked<RoleItem, RoleData>{
    @Resource
    private LogTemplate logTemplate;
    @Resource
    private LoaderHandler<RoleItem> loaderHandler;
    @Resource
    private SetterHandler<RoleData> setterHandler;

    @Override
    public RoleData runLinked(RoleItem roleItem) throws RuntimeException{
        LoaderHandlerResp resp = loaderHandler.run(roleItem);
        List<Exception> exceptions = resp.getExceptions();
        if(exceptions != null && exceptions.size() != 0){
            for(Exception e: exceptions){
                logTemplate.error(roleItem.getRequestId(), AppConfiguration.PSM, e.getMessage());
            }
            throw new RuntimeException("loader err: " + exceptions.size() + " loader throw exception");
        }
        RoleData roleData = new RoleData();
        setterHandler.run(resp.getAttributes(),roleData);
        return roleData;
    }
}
