package pers.crayon.user.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import io.swagger.annotations.Api;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.crayon.user.shiro.CustomRealm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/12/8 11:47
 * @since JDK 1.8
 */

@Configuration
@Api(tags = "ShiroConfig", description = "把CustomRealm和SecurityManager等注入到spring容器中")
public class ShiroConfig {

    // 1、将自己的验证方式加入容器 自定义 realm
    @Bean
    public CustomRealm myShiroRealm() {
        CustomRealm customRealm = new CustomRealm();
        return customRealm;
    }

    // 2、权限管理，配置主要是Realm的管理认证 返回 SessionsSecurityManager/DefaultWebSecurityManager
    @Bean(name = "MySecurityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    // 3、Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("MySecurityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /*
         添加Shiro内置过滤器 ——这里可以控制每个页面的访问权限
         anon: 无需认证即可访问
         authc: 必须认证才可以访问
         user: 必须拥有记住我功能才可以访问？
         perms: 拥有 对某个资源的权限 才可以访问
         role: 拥有某个角色权限才可以访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/logout", "logout");
        filterMap.put("/login/*", "authc");
        // 可以在map里为接口添加授权，只有拥有某项权限的人才可以
        filterMap.put("/admin/*", "perms[admin]");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        // 设置登录页面,需要登录的则跳转
        shiroFilterFactoryBean.setLoginUrl("/login/login");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/home/homepage");
        //未授权页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");


        return shiroFilterFactoryBean;
    }

    //ShiroDialect : 用于整合 thymeleaf 与 shiro
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}