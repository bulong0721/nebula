package io.nebula.socketio.impl;

import com.corundumstudio.socketio.protocol.Packet;
import com.corundumstudio.socketio.protocol.PacketType;
import com.corundumstudio.socketio.store.pubsub.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nebula.socketio.RedissonWsApi;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/5
 */
@Component
public class RedissonWsApiImpl implements RedissonWsApi {
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private ObjectMapper objectMapper;

    protected void publish(String topic, PubSubMessage msg) {
        redissonClient.getTopic(topic).publish(msg);
    }

    @Override
    public void join(String namespace, String room, UUID sessionId) {
        JoinLeaveMessage msg = new JoinLeaveMessage(sessionId, room, namespace);
        publish(PubSubType.JOIN.toString(), msg);
    }

    @Override
    public void leave(String namespace, String room, UUID sessionId) {
        JoinLeaveMessage msg = new JoinLeaveMessage(sessionId, room, namespace);
        publish(PubSubType.LEAVE.toString(), msg);
    }

    @Override
    public void send(String namespace, UUID sessionId, String event, Object... data) {
        Packet packet = new Packet(PacketType.MESSAGE);
        packet.setSubType(PacketType.EVENT);
        packet.setName(event);
        packet.setData(toMaps(data));
        packet.setNsp(namespace);
        SendMessage msg = new SendMessage(sessionId, namespace, packet);
        publish("SEND", msg);
    }

    @Override
    public void dispatch(String namespace, String room, String event, Object... data) {
        Packet packet = new Packet(PacketType.MESSAGE);
        packet.setSubType(PacketType.EVENT);
        packet.setName(event);
        packet.setData(toMaps(data));
        packet.setNsp(namespace);
        DispatchMessage msg = new DispatchMessage(room, packet, namespace);
        publish(PubSubType.DISPATCH.toString(), msg);
    }

    private List<Object> toMaps(Object... data) {
        List<Object> result = new ArrayList<>();
        for (Object elem : data) {
            try {
                JsonNode node = objectMapper.convertValue(elem, JsonNode.class);
                result.add(node);
            } catch (Exception e) {
            }
        }
        return result;
    }

}
