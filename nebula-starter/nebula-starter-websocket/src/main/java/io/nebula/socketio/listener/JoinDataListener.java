package io.nebula.socketio.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/8
 */
@Slf4j
public class JoinDataListener implements DataListener<JoinLeaveEvent> {
    @Override
    public void onData(SocketIOClient client, JoinLeaveEvent data, AckRequest ackSender) throws Exception {
        if (null == data || null == data.getRooms()) {
            log.warn("Client={}没有可加入的房间，Event={}", client, data);
            return;
        }
        String nsp = client.getNamespace().getName();
        for (String room : data.getRooms()) {
            client.joinRoom(room);
            log.info("Client={}加入Namespace={}房间，Room={}", client, nsp, room);
        }
    }
}
