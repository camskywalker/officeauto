package com.lizijian.officeauto.Config.security;

import com.lizijian.officeauto.mapper.UrlMapper;
import com.lizijian.officeauto.pojo.Role;
import com.lizijian.officeauto.pojo.Url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class MyMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    UrlMapper urlMapper;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<Url> allUrls = urlMapper.getUrl();
        for (Url url : allUrls) {
            if (antPathMatcher.match(url.getHttpUrl(), requestUrl)) {
                List<Role> needRoles = url.getNeedRoles();
                String[] needRoleNames = new String[needRoles.size()];
                for (int i = 0; i < needRoleNames.length; i++) {
                    needRoleNames[i] = needRoles.get(i).getName();
                }
                return SecurityConfig.createList(needRoleNames);
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
