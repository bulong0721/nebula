package io.nebula.socketio;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;
import io.nebula.properties.Namespace;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/5
 */
@Slf4j
public class CookieAuthorizationListener implements AuthorizationListener {
    private final Map<String, Namespace> namespaces;

    public CookieAuthorizationListener(Map<String, Namespace> namespaces) {
        this.namespaces = namespaces;
    }

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
        String nsp = data.getSingleUrlParam("nsp");
        HttpHeaders headers = data.getHttpHeaders();
        if (StringUtil.isBlank(nsp)) {
            return null;
        }
        Namespace namespace = namespaces.get(nsp);
        if (null == namespace) {
            return null;
        }
        String cookieName = namespace.getAuthorizedCookie();
        for (String cookieHeader : headers.getAll(HttpHeaderNames.COOKIE)) {
            Set<Cookie> cookies = ServerCookieDecoder.LAX.decode(cookieHeader);
            for (Cookie cookie : cookies) {
                if (cookie.name().equals(cookieName)) {
                    try {
                        return UUID.fromString(cookie.value());
                    } catch (IllegalArgumentException iaex) {
                        log.warn("Malformed UUID received for session! io=" + cookie.value());
                        return UUID.randomUUID();
                    }
                }
            }
        }
        return null;
    }
}
