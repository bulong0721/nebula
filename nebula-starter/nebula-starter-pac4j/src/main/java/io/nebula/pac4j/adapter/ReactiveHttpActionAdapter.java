package io.nebula.pac4j.adapter;

import io.nebula.pac4j.context.ReactiveContext;
import lombok.extern.slf4j.Slf4j;
import org.pac4j.core.exception.http.HttpAction;
import org.pac4j.core.http.adapter.HttpActionAdapter;
import reactor.core.publisher.Mono;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/20
 */
@Slf4j
public class ReactiveHttpActionAdapter implements HttpActionAdapter<Mono<Void>, ReactiveContext> {

    public static final ReactiveHttpActionAdapter INSTANCE = new ReactiveHttpActionAdapter();

//    @Override
//    public Mono<Void> adapt(int code, ReactiveContext context) {
//        log.debug("requires HTTP action: {}", code);
//        switch (code) {
//            case HttpConstants.UNAUTHORIZED:
//                return ContextUtil.unauthorized(context);
//            case HttpConstants.FORBIDDEN:
//                return ContextUtil.forbidden(context);
////            case HttpConstants.TEMP_REDIRECT:
////                return ContextUtil.redirect(context);
//            case HttpConstants.BAD_REQUEST:
//                return ContextUtil.badRequest(context);
//            case HttpConstants.OK:
//                return ContextUtil.ok(context);
//            case HttpConstants.NO_CONTENT:
//                return ContextUtil.noContent(context);
//        }
//        final String message = "Unsupported HTTP action: " + code;
//        log.error(message);
//        throw new TechnicalException(message);
//    }

    @Override
    public Mono<Void> adapt(HttpAction action, ReactiveContext context) {
        return null;
    }
}
