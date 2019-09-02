package io.nebula.socketio;

import java.util.UUID;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/5
 */
public interface RedissonWsApi {
    /**
     * 加入房间
     *
     * @param namespace
     * @param room
     * @param sessionId
     */
    void join(String namespace, String room, UUID sessionId);

    /**
     * 离开房间
     *
     * @param namespace
     * @param room
     * @param sessionId
     */
    void leave(String namespace, String room, UUID sessionId);

    /**
     * 向指定客户发送消息
     *
     * @param namespace
     * @param sessionId
     * @param data
     */
    void send(String namespace, UUID sessionId, String event, Object... data);

    /**
     * 向指定房间分发消息
     *
     * @param namespace
     * @param room
     * @param data
     */
    void dispatch(String namespace, String room, String event, Object... data);
}
