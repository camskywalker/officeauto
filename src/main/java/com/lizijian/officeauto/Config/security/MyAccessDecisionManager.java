package com.lizijian.officeauto.Config.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(//当前用户的认证，可以获取权限、用户名等信息。
                       Authentication authentication,
                       //过滤对象，可以获得request等等对象。
                       Object o,
                       //当前网址所需要的角色。
                       Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (ConfigAttribute configAttribute : collection) {
            if ("ROLE_LOGIN".equals(configAttribute.getAttribute()) && authentication instanceof UsernamePasswordAuthenticationToken){
                return;
            }
            for (GrantedAuthority authority : authorities){
                if (configAttribute.getAttribute().equals(authority.getAuthority())){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
