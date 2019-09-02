package io.nebula.socketio.store;

import com.corundumstudio.socketio.store.RedissonPubSubStore;
import com.corundumstudio.socketio.store.pubsub.PubSubListener;
import com.corundumstudio.socketio.store.pubsub.PubSubMessage;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/5
 */
public class EnhancedPubSubStore extends RedissonPubSubStore {
    private final RedissonClient redissonSub;
    private final Long nodeId;

    public EnhancedPubSubStore(RedissonClient redissonPub, RedissonClient redissonSub, Long nodeId) {
        super(redissonPub, redissonSub, nodeId);
        this.redissonSub = redissonSub;
        this.nodeId = nodeId;
    }

    public <T extends PubSubMessage> void subscribe(String name, final PubSubListener<T> listener, Class<T> clazz) {
        RTopic topic = redissonSub.getTopic(name);
        int regId = topic.addListener(PubSubMessage.class, new MessageListener<PubSubMessage>() {
            @Override
            public void onMessage(CharSequence channel, PubSubMessage msg) {
                if (!nodeId.equals(msg.getNodeId())) {
                    listener.onMessage((T) msg);
                }
            }
        });
    }
}
