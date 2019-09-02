package io.nebula.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/10/24
 */
public class ServletUtil {
    public static final String DETAILS_KEY = "X-Logs-Details";
    public static final String REQUEST_ID = "X-Request-Id";

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static String fetchRequestId() {
        String requestId = (String) getRequest().getAttribute(REQUEST_ID);
        if (requestId == null) {
            requestId = Optional.ofNullable(getRequest().getHeader(REQUEST_ID)).orElse("x-" + UUID.randomUUID());
            getRequest().setAttribute(REQUEST_ID, requestId);
        }
        return requestId;
    }
}
