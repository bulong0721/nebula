package io.nebula.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.nebula.cache.RedissonShiroCacheManager;
import io.nebula.kernel.security.UserService;
import io.nebula.properties.WebProperties;
import io.nebula.session.RedissonSessionDao;
import io.nebula.session.RedissonWebSessionManager;
import io.nebula.util.StringUtil;
import io.nebula.web.shiro.*;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/12/21
 */
@EnableConfigurationProperties(WebProperties.class)
@Configuration
@ConditionalOnClass({RedissonClient.class, UserService.class})
public class SpringShiroConfigurer {
    @Autowired
    private WebProperties webProperties;
    @Autowired
    private RedissonClient client;
    @Autowired(required = false)
    private UserService userService;

    @Bean
    public Realm realm() {
        NebulaRealm realm = new NebulaRealm();
        realm.setUserService(userService);
        return realm;
    }

    @Bean
    public CacheManager cacheManager() {
        return new RedissonShiroCacheManager(client);
    }

    @Bean
    public DefaultWebSecurityManager securityManager(Realm realm, SessionManager sessionManager, CacheManager cacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setCacheManager(cacheManager);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactory(WebSecurityManager securityManager, ObjectMapper objectMapper) {
        WebProperties.Shiro shiro = webProperties.getShiro();
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl(shiro.getLoginUrl());
        shiroFilterFactoryBean.setSuccessUrl(shiro.getSuccessUrl());
        // 验证失败不进行重定向，使用json直接返回
        if (shiro.isNotRedirect()) {
            shiroFilterFactoryBean.getFilters().put("authc", new DirectAuthenticationFilter(objectMapper));
            shiroFilterFactoryBean.getFilters().put("perms", new DirectPermissionFilter(objectMapper));
            shiroFilterFactoryBean.getFilters().put("roles", new DirectRolesFilter(objectMapper));
            shiroFilterFactoryBean.getFilters().put("logout", new DirectLogoutFilter(objectMapper));
            shiroFilterFactoryBean.getFilters().put("user", new DirectUserFilter(objectMapper));
        }
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put(shiro.getLogoutUrl(), "logout");
        filterChainDefinitionMap.put(shiro.getLoginUrl(), "authc");
        if (null != shiro.getFilters()) {
            for (WebProperties.FilterDef filterDef : shiro.getFilters()) {
                String[] urls = StringUtil.splitc(filterDef.getUrls(), ',');
                for (String url : urls) {
                    filterChainDefinitionMap.put(url, filterDef.getExpr());
                }
            }
        } else {
            filterChainDefinitionMap.put("/**", "user");
        }
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SessionDAO sessionDAO() {
        WebProperties.Session session = webProperties.getSession();
        RedissonSessionDao sessionDao = new RedissonSessionDao(session.getNamespace());
        sessionDao.setRedisson(client);
        return sessionDao;
    }

    @Bean("sessionManager")
    public WebSessionManager webSessionManager() {
        RedissonWebSessionManager sessionManager = new RedissonWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO());
        return sessionManager;
    }
}
