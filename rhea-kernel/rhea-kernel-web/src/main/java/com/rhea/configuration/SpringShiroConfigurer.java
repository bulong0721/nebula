package com.rhea.configuration;

import com.rhea.resisson.cache.RedissonShiroCacheManager;
import com.rhea.resisson.session.RedissonSessionDao;
import com.rhea.resisson.session.RedissonWebSessionManager;
import com.rhea.kernel.security.RoleService;
import com.rhea.kernel.security.UserService;
import com.rhea.properties.WebProperties;
import com.rhea.web.shiro.RheaRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @author xubulong
 * @version V1.0
 */
@EnableConfigurationProperties(WebProperties.class)
@Configuration
@ConditionalOnClass({RedissonClient.class, UserService.class, RoleService.class})
public class SpringShiroConfigurer {
    @Autowired
    private WebProperties webProperties;
    @Autowired
    private RedissonClient client;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Bean
    public Realm realm() {
        RheaRealm realm = new RheaRealm();
        realm.setUserService(userService);
        realm.setRoleService(roleService);
        return realm;
    }

    @Bean
    protected CacheManager cacheManager() {
        return new RedissonShiroCacheManager(client);
    }

    @Bean
    DefaultWebSecurityManager securityManager(Realm realm, SessionManager sessionManager, CacheManager cacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setCacheManager(cacheManager);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactory(WebSecurityManager securityManager) {
        WebProperties.Shiro shiro = webProperties.getShiro();
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl(shiro.getLoginUrl());
        shiroFilterFactoryBean.setSuccessUrl(shiro.getSuccessUrl());
        shiroFilterFactoryBean.setUnauthorizedUrl(shiro.getPublicUrl());
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put(shiro.getLogoutUrl(), "logout");
        filterChainDefinitionMap.put("/actuator/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Autowired
    SessionDAO sessionDAO() {
        RedissonSessionDao sessionDao = new RedissonSessionDao();
        sessionDao.setRedisson(client);
        return sessionDao;
    }

    @Bean("sessionManager")
    WebSessionManager webSessionManager() {
        RedissonWebSessionManager sessionManager = new RedissonWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO());
        return sessionManager;
    }
}
