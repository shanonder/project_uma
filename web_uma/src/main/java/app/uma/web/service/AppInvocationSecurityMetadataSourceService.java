package app.uma.web.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import app.uma.dao.entity.Permission;
import app.uma.dao.repository.PermissionRepository;

/**
 * Created by yangyibo on 17/1/19.
 */
@Service
public class AppInvocationSecurityMetadataSourceService  implements
        FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionRepository permissionRepo;

    private HashMap<String, Collection<ConfigAttribute>> map =null;

    /**
     * 加载资源，初始化资源变量
     */
    public void loadResourceDefine(){
        map = new HashMap<>();
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;
        Iterable<Permission> permissions = permissionRepo.findAll();
        if(permissions != null){
            for(Permission permission : permissions) {
                array = new ArrayList<>();
                cfg = new SecurityConfig(permission.getName());
                array.add(cfg);
                map.put(permission.getUrl(), array);
            }
        }


    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if(map ==null) loadResourceDefine();
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
                return map.get(resUrl);
            }
        }
        return null;
    }

    private Collection<ConfigAttribute> configAttrbutes;
    
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return configAttrbutes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
