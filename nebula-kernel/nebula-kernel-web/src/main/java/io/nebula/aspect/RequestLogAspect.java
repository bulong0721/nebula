package io.nebula.aspect;

import io.nebula.kernel.Constants;
import io.nebula.kernel.security.IUser;
import io.nebula.util.ServletUtil;
import io.nebula.util.SessionUtil;
import io.nebula.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/10/15
 */
@Aspect
@AllArgsConstructor
public class RequestLogAspect implements Ordered {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLogAspect.class);
    private final int order;

    @Around(value = "within(io.nebula..*) " +
            "&& (@annotation(org.springframework.web.bind.annotation.ResponseBody)" +
            "|| @annotation(org.springframework.web.bind.annotation.RequestMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping)" +
            ") ")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        // 生成请求日志
        RequestLogger requestLog = generateJsonRequestDetails();
        // 执行真实请求
        final Object proceed = joinPoint.proceed();
        // 当响应完成时, 打印完整的'request & response'信息
        requestLog.setResponseTime(OffsetDateTime.now());
        LOGGER.info("RequestLoggingAspect#\r\nREQUEST->\r\n{}\r\nRESPONSE->\r\n {}", requestLog, new ResponseLogger());
        // 放行
        return proceed;
    }

    /**
     * 创建通用的日志输出模式并绑定线程
     *
     * @return 日志模型
     */
    private RequestLogger generateJsonRequestDetails() {
        RequestLogger logDetails = (RequestLogger) ServletUtil.getRequest().getAttribute(ServletUtil.DETAILS_KEY);
        if (logDetails == null) {
            logDetails = new RequestLogger();
            ServletUtil.getRequest().setAttribute(ServletUtil.DETAILS_KEY, logDetails);
        }
        return logDetails;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Data
    private class RequestLogger {
        private final HttpServletRequest request = ServletUtil.getRequest();

        private final IUser user = SessionUtil.getAttribute(Constants.SESSION_USER, request);

        private String requestId = ServletUtil.fetchRequestId();

        private String url = request.getRequestURL().toString();

        private String method = request.getMethod();

        private String remoteHost = StringUtil.isNotBlank(request.getHeader("X-Real-IP")) ? request.getHeader("X-Real-IP") : request.getRemoteHost();

        private OffsetDateTime requestTime = OffsetDateTime.now();

        private OffsetDateTime responseTime;

        private String apiDesc;

        private String userName = (user == null ? "" : user.getUserName());

        @Override
        public String toString() {
            return "RequestLogger{" +
                    "requestId='" + requestId + '\'' +
                    ", url='" + url + '\'' +
                    ", method='" + method + '\'' +
                    ", remoteHost='" + remoteHost + '\'' +
                    ", requestTime=" + requestTime +
                    ", responseTime=" + responseTime +
                    ", apiDesc='" + apiDesc + '\'' +
                    ", userName='" + userName + '\'' +
                    '}';
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class ResponseLogger {

        private Object responseBody;
    }
}
