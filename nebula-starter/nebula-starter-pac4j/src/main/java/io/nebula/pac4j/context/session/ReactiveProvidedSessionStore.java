package io.nebula.pac4j.context.session;

import io.nebula.pac4j.context.ReactiveContext;
import org.pac4j.core.util.CommonHelper;
import org.springframework.web.server.WebSession;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/20
 */
class ReactiveProvidedSessionStore extends ReactiveSessionStore {
    private final WebSession session;

    public ReactiveProvidedSessionStore(final WebSession session) {
        CommonHelper.assertNotNull("session", session);
        this.session = session;
    }

    @Override
    protected WebSession getHttpSession(final ReactiveContext context) {
        return session;
    }
}
