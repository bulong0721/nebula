package io.nebula.socketio;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;
import io.netty.handler.codec.http.HttpHeaders;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/5
 */
@Slf4j
public class TrustAuthorizationListener implements AuthorizationListener {

    @Override
    public boolean isAuthorized(HandshakeData data) {
        HttpHeaders httpHeaders = data.getHttpHeaders();
        UUID sessionId = getSessionId(data);
        if (null != sessionId) {
            httpHeaders.set("io", sessionId.toString());
            return true;
        }
        return false;
    }

    private UUID getSessionId(HandshakeData data) {
        return UUID.randomUUID();
    }
}
