package com.dc.security.service;

 
import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
 
//Security需要用到一个实现了AccessDecisionManager接口的类
//类功能：根据当前用户的信息，和目标url涉及到的权限，判断用户是否可以访问
//判断规则：用户只要匹配到目标url权限中的一个role就可以访问
@Component
public class MyAccessDecisionManager implements AccessDecisionManager{
	private Logger logger = LoggerFactory.getLogger(MyAccessDecisionManager.class);
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
 
        //迭代器遍历目标url的权限列表
        Iterator<ConfigAttribute> iterator = collection.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute ca = iterator.next();
 
            String needRole = ca.getAttribute();
            if ("ROLE_LOGIN".equals(needRole)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new BadCredentialsException("未登录");
                } else
                    return;
            }
 
            //遍历当前用户所具有的权限
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
            if (logger.isWarnEnabled()) {
            	logger.warn("当前用户没有编号为【{}】的权限，请联系管理员", needRole);
            }
            throw new AccessDeniedException("当前用户没有编号为【" + needRole + "】的权限，请联系管理员");
        }
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