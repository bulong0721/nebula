package io.nebula.pac4j.util;

import io.nebula.pac4j.context.ReactiveContext;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/21
 */
public final class ContextUtil {

    /**
     * 未验证
     *
     * @param context
     * @return
     */
    public static Mono<Void> unauthorized(ReactiveContext context) {
        return status(context, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 禁止
     *
     * @param context
     * @return
     */
    public static Mono<Void> forbidden(ReactiveContext context) {
        return status(context, HttpStatus.FORBIDDEN);
    }

    /**
     * 重定向
     *
     * @param context
     * @return
     */
    public static Mono<Void> redirect(ReactiveContext context) {
        return status(context, HttpStatus.TEMPORARY_REDIRECT);
    }

    /**
     * 坏请求
     *
     * @param context
     * @return
     */
    public static Mono<Void> badRequest(ReactiveContext context) {
        return status(context, HttpStatus.BAD_REQUEST);
    }

    /**
     * @param context
     * @return
     */
    public static Mono<Void> ok(ReactiveContext context) {
        return status(context, HttpStatus.OK);
    }

    /**
     * 空内容
     *
     * @param context
     * @return
     */
    public static Mono<Void> noContent(ReactiveContext context) {
        return status(context, HttpStatus.NO_CONTENT);
    }

    /**
     * 状态修改
     *
     * @param context
     * @param status
     * @return
     */
    public static Mono<Void> status(ReactiveContext context, HttpStatus status) {
        ServerHttpResponse response = context.getResponse();
        response.setStatusCode(status);
        if (status.isError()) {
            DataBuffer dataBuffer = response.bufferFactory().wrap(status.getReasonPhrase().getBytes());
            response.writeWith(Flux.just(dataBuffer));
            return response.setComplete();
        }
        return Mono.empty();
    }
}
