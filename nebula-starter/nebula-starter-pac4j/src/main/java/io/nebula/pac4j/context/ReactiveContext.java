package io.nebula.pac4j.context;

import com.google.common.collect.Maps;
import io.nebula.pac4j.context.session.ReactiveSessionStore;
import org.pac4j.core.context.Cookie;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.context.session.SessionStore;
import org.pac4j.core.util.CommonHelper;
import org.springframework.http.HttpCookie;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.support.WebExchangeDataBinder;
import org.springframework.web.server.ServerWebExchange;

import java.util.*;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/20
 */
public class ReactiveContext implements WebContext {
    private final ServerWebExchange exchange;
    private final ServerHttpRequest request;
    private final ServerHttpResponse response;
    private Map<String, Object> paramMap = new HashMap<>();
    private SessionStore<ReactiveContext> sessionStore;

    public ReactiveContext(final ServerWebExchange exchange) {
        this(exchange, new ReactiveSessionStore());
    }

    public ReactiveContext(final ServerWebExchange exchange, final SessionStore<ReactiveContext> sessionStore) {
        CommonHelper.assertNotNull("exchange", exchange);
        this.exchange = exchange;
        this.request = exchange.getRequest();
        this.response = exchange.getResponse();

        WebExchangeDataBinder.extractValuesToBind(exchange).subscribe(x -> {
                    x.forEach((key, value) -> paramMap.put(key, value));
                }
        );

        if (sessionStore == null) {
            this.sessionStore = new ReactiveSessionStore();
        } else {
            this.sessionStore = sessionStore;
        }
    }

    @Override
    public SessionStore getSessionStore() {
        return sessionStore;
    }

    @Override
    public Optional<String> getRequestParameter(String name) {
        return Optional.empty();
    }

//    @Override
//    public String getRequestParameter(String name) {
//        Object value = paramMap.get(name);
//        if (value instanceof List) {
//            return String.valueOf(((List) value).get(0));
//        } else {
//            return (String) value;
//        }
//    }

    @Override
    public Map<String, String[]> getRequestParameters() {
        return Maps.newTreeMap();
    }

    @Override
    public Optional getRequestAttribute(String name) {
        return Optional.empty();
    }

//    @Override
//    public Object getRequestAttribute(String name) {
//        return exchange.getAttribute(name);
//    }

    @Override
    public void setRequestAttribute(String name, Object value) {
        exchange.getAttributes().put(name, value);
    }

    @Override
    public Optional<String> getRequestHeader(String name) {
        return Optional.empty();
    }

//    @Override
//    public String getRequestHeader(String name) {
//        return request.getHeaders().getFirst(name);
//    }

    @Override
    public String getRequestMethod() {
        return request.getMethodValue();
    }

    @Override
    public String getRemoteAddr() {
        return request.getRemoteAddress().getHostString();
    }

//    @Override
//    public void writeResponseContent(String content) {
//        response.writeWith(Flux.just(content).map(bx -> response.bufferFactory().wrap(bx.getBytes())));
//    }
//
//    @Override
//    public void setResponseStatus(int code) {
//        HttpStatus httpStatus = HttpStatus.valueOf(code);
//        this.response.setStatusCode(httpStatus);
//    }

    @Override
    public void setResponseHeader(String name, String value) {
        response.getHeaders().set(name, value);
    }

    @Override
    public void setResponseContentType(String content) {

        MediaType mediaType = MediaType.valueOf(content);
        response.getHeaders().setContentType(mediaType);
    }

    @Override
    public String getServerName() {
        return request.getRemoteAddress().getHostName();
    }

    @Override
    public int getServerPort() {
        return request.getRemoteAddress().getPort();
    }

    @Override
    public String getScheme() {
        return request.getURI().getScheme();
    }

    @Override
    public boolean isSecure() {
        return null != request.getSslInfo();
    }

    @Override
    public String getFullRequestURL() {
        return request.getPath().value();
    }

    @Override
    public Collection<Cookie> getRequestCookies() {
        final Collection<Cookie> pac4jCookies = new LinkedHashSet<>();
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        if (cookies != null) {
            for (final List<HttpCookie> list : request.getCookies().values()) {
                for (HttpCookie c : list) {
                    final Cookie cookie = new Cookie(c.getName(), c.getValue());
                    pac4jCookies.add(cookie);
                }
            }
        }
        return pac4jCookies;
    }

    @Override
    public void addResponseCookie(Cookie cookie) {
        final ResponseCookie c = ResponseCookie.from(cookie.getName(), cookie.getValue())
                .secure(cookie.isSecure())
                .path(cookie.getPath())
                .maxAge(cookie.getMaxAge())
                .httpOnly(cookie.isHttpOnly())
                .domain(cookie.getDomain())
                .build();
        response.addCookie(c);
    }

    @Override
    public String getPath() {
        return request.getPath().value();
    }

    public ServerHttpRequest getRequest() {
        return request;
    }

    public ServerHttpResponse getResponse() {
        return response;
    }

    public ServerWebExchange getExchange() {
        return exchange;
    }
}
