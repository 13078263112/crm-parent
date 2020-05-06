package com.ywc.crm.eim.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.crm.eim.entity.Permission;
import com.ywc.crm.eim.service.PermissionService;
import com.ywc.crm.eim.shiro.MyRealm;
import com.ywc.crm.eim.vo.RolePermissionVo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 嘟嘟~
 * @date 2020/3/23 17:42
 */
@Configuration
public class ShiroConfig {
    @Reference
    PermissionService permissionService;
    @Bean
    MyRealm myRealm(@Autowired HashedCredentialsMatcher hashedCredentialsMatcher){
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return myRealm ;
    }
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1024);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }
    @Bean
    public DefaultWebSecurityManager securityManager(@Autowired MyRealm myRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        return securityManager;
    }
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean filterBean = new ShiroFilterFactoryBean();
        filterBean.setSecurityManager(securityManager);
        Map<String,String> filterMap = new LinkedHashMap<String,String>();
        filterMap.put("/login","anon");   //允许登录判断url
        //这里的/static指的是url的static  【resource/static/static/**】
        filterMap.put("/css/**","anon");
        filterMap.put("/fonts/**","anon");
        filterMap.put("/js/**","anon");
        filterMap.put("/images/**","anon");
        filterMap.put("/fonts/**","anon");
        filterMap.put("/lib/**", "anon");
        filterMap.put("/logout", "logout");

        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->

        List<Permission> lists = permissionService.queryAll();
        for (Permission list : lists) {

            filterMap.put(list.getUrl(), "perms["+list.getPermission()+"]");

        }
        System.out.println(filterMap.toString());
        filterMap.put("/**", "authc");
        filterBean.setLoginUrl("/toLogin");

        filterBean.setFilterChainDefinitionMap(filterMap);
        return filterBean;
    }
    @Bean
    public ShiroDialect shiroDialect(){
        return  new ShiroDialect();
    }

}
