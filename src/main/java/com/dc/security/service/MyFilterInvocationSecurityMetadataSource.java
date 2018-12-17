package com.dc.security.service;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.dc.security.constans.CommonConstant;

@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
   private Logger logger = LoggerFactory.getLogger(MyFilterInvocationSecurityMetadataSource.class);
 
    @Override
    //接收用户请求的地址，返回访问该地址需要的所有权限
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //得到用户的请求地址,控制台输出一下
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        if(logger.isInfoEnabled())
          logger.info("用户请求的地址是：" + requestUrl);
 
        //如果登录页面就不需要权限
        if ("/login".equals(requestUrl)) {
            return null;
        }
 
        //根据url访问携带的参数auth_code表示当前url需要的权限编号。在MyAccessDecisionManager获取到用户的权限和这个权限编号进行比较
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
		// 获取auth_code请求参数
		String authCode = request.getParameter(CommonConstant.AUTHORIZATION_CODE);
		if (authCode != null) {
			return SecurityConfig.createList(authCode);
		} else if (authCode == null || authCode.isEmpty()) {
			throw new AccessDeniedException("认证失败，缺少认证参数：" + CommonConstant.AUTHORIZATION_CODE);
		}
		return null;
    }
 
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
 
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
