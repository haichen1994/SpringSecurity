package com.dc.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private UserDetailServiceImpl userService;
 
    //根据一个url请求，获得访问它所需要的roles权限
    @Autowired
    MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;
 
    //接收一个用户的信息和访问一个url所需要的权限，判断该用户是否可以访问
    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;
 
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;
    
    @Autowired
    private AuthenticationSucessHandler sucessHandler;
    
    @Autowired
    private AuthenticationFailureHandler failHandler;
 
    /**定义认证用户信息获取来源，密码校验规则等*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new SecurityPassEncoder());
    }
 
    //在这里配置哪些页面不需要认证
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/", "/index");
    }
 
    /**定义安全策略*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()       //配置安全策略
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

					@Override
					public <O extends FilterSecurityInterceptor> O postProcess(O o) {
						  o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
	                        o.setAccessDecisionManager(myAccessDecisionManager);
	                        return o;
					}
                })
//                .antMatchers("/hello").hasAuthority("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .failureHandler(failHandler)
                .successHandler(sucessHandler)
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler);
    }
}