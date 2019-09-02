package io.nebula.pac4j.context.session;

import io.nebula.pac4j.context.ReactiveContext;
import org.pac4j.core.context.session.SessionStore;
import org.springframework.web.server.WebSession;

import java.util.Optional;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/20
 */
public class ReactiveSessionStore implements SessionStore<ReactiveContext> {

    protected WebSession getHttpSession(final ReactiveContext context) {
        return context.getExchange().getSession().block();
    }

    @Override
    public String getOrCreateSessionId(ReactiveContext context) {
        return getHttpSession(context).getId();
    }

    @Override
    public Optional<Object> get(ReactiveContext context, String key) {
        return getHttpSession(context).getAttribute(key);
    }

    @Override
    public void set(ReactiveContext context, String key, Object value) {
        if (value == null) {
            getHttpSession(context).getAttributes().remove(key);
        } else {
            getHttpSession(context).getAttributes().put(key, value);
        }
    }

    @Override
    public boolean destroySession(ReactiveContext context) {
        getHttpSession(context).invalidate();
        return true;
    }

    @Override
    public Optional getTrackableSession(ReactiveContext context) {
        return Optional.ofNullable(getHttpSession(context));
    }

    @Override
    public Optional<SessionStore<ReactiveContext>> buildFromTrackableSession(ReactiveContext context, Object trackableSession) {
//        if (trackableSession != null) {
//            return new ReactiveProvidedSessionStore((WebSession) trackableSession);
//        } else {
//            return null;
//        }
        return null;
    }

    @Override
    public boolean renewSession(ReactiveContext context) {
        return false;
    }
}
