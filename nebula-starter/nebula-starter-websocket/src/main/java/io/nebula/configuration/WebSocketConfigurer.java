package io.nebula.configuration;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.store.StoreFactory;
import com.corundumstudio.socketio.store.pubsub.PubSubType;
import io.nebula.properties.Namespace;
import io.nebula.properties.Scheduler;
import io.nebula.properties.WebSocketProperties;
import io.nebula.socketio.CookieAuthorizationListener;
import io.nebula.socketio.TrustAuthorizationListener;
import io.nebula.socketio.listener.JoinDataListener;
import io.nebula.socketio.listener.JoinLeaveEvent;
import io.nebula.socketio.listener.LeaveDataListener;
import io.nebula.socketio.store.EnhancedStoreFactory;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.Map;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/5
 */
@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties(WebSocketProperties.class)
public class WebSocketConfigurer {
    @Autowired
    private WebSocketProperties webSocketProperties;

    @Bean
    @ConditionalOnClass({RedissonClient.class})
    public EnhancedStoreFactory storeFactory(RedissonClient redissonClient) {
        return new EnhancedStoreFactory(redissonClient);
    }

    @Bean
    @Profile({"dev", "sit", "uat"})
    public TrustAuthorizationListener trustedAuthorizationListener() {
        return new TrustAuthorizationListener();
    }

    @Bean
    @Profile("pro")
    public CookieAuthorizationListener cookieAuthorizationListener() {
        Map<String, Namespace> namespaces = webSocketProperties.getNamespaces();
        if (null != namespaces) {
            return new CookieAuthorizationListener(namespaces);
        }
        return null;
    }

    @Bean
    @ConditionalOnProperty("nebula.websocket.port")
    public Configuration configuration(StoreFactory storeFactory, AuthorizationListener authorizationListener) {
        Configuration config = new Configuration();
        config.setPort(webSocketProperties.getPort());
        Scheduler ping = webSocketProperties.getPing();
        if (null != ping) {
            config.setPingInterval(ping.getInterval());
            config.setPingTimeout(ping.getTimeout());
        }
        if (null != storeFactory) {
            config.setStoreFactory(storeFactory);
        }
        if (null != authorizationListener) {
            config.setRandomSession(false);
            config.setAuthorizationListener(authorizationListener);
        }
        return config;
    }

    @Bean(initMethod = "startAsync", destroyMethod = "stop")
    @ConditionalOnBean(Configuration.class)
    public SocketIOServer socketIOServer(Configuration configuration) {
        SocketIOServer socketIOServer = new SocketIOServer(configuration);
        Map<String, Namespace> namespaces = webSocketProperties.getNamespaces();
        if (null != namespaces) {
            for (String nsp : namespaces.keySet()) {
                SocketIONamespace namespace = socketIOServer.addNamespace("/" + nsp);
                namespace.addEventListener(PubSubType.JOIN.name(), JoinLeaveEvent.class, new JoinDataListener());
                namespace.addEventListener(PubSubType.LEAVE.name(), JoinLeaveEvent.class, new LeaveDataListener());
            }
        }
        return socketIOServer;
    }
}
