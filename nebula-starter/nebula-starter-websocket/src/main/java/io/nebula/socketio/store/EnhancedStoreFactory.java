package io.nebula.socketio.store;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.handler.AuthorizeHandler;
import com.corundumstudio.socketio.namespace.Namespace;
import com.corundumstudio.socketio.namespace.NamespacesHub;
import com.corundumstudio.socketio.protocol.JsonSupport;
import com.corundumstudio.socketio.store.RedissonStoreFactory;
import com.corundumstudio.socketio.store.pubsub.PubSubListener;
import com.corundumstudio.socketio.store.pubsub.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/5
 */
@Slf4j
public class EnhancedStoreFactory extends RedissonStoreFactory {
    private final EnhancedPubSubStore pubSubStore;

    public EnhancedStoreFactory(RedissonClient redisson) {
        super(redisson);
        pubSubStore = new EnhancedPubSubStore(redisson, redisson, getNodeId());
    }

    @Override
    public void init(NamespacesHub namespacesHub, AuthorizeHandler authorizeHandler, JsonSupport jsonSupport) {
        super.init(namespacesHub, authorizeHandler, jsonSupport);
        pubSubStore.subscribe("SEND", new PubSubListener<SendMessage>() {
            @Override
            public void onMessage(SendMessage msg) {
                Namespace namespace = namespacesHub.get(msg.getNamespace());
                if (null == namespace) {
                    log.warn("Namespace:{} not found", msg.getNamespace());
                    return;
                }
                SocketIOClient client = namespace.getClient(msg.getSessionId());
                if (null == client) {
                    log.warn("Session:{} not found in namespace:{}", msg.getSessionId(), msg.getNamespace());
                    return;
                }
                client.send(msg.getPacket());
                log.info("向Namespace={}的Session={}发送消息{}", namespace.getName(), msg.getSessionId(), msg.getPacket());
            }
        }, SendMessage.class);
    }

}
