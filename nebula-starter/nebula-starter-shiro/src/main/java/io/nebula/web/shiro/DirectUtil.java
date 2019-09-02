package io.nebula.web.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.nebula.kernel.exchange.ResponseEntity;
import io.nebula.kernel.exchange.StatusCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/1/2
 */
public class DirectUtil {

    /**
     * 验证失败
     *
     * @param request
     * @param response
     * @param objectMapper
     * @throws Exception
     */
    public static void onAccessDenied(ServletRequest request, ServletResponse response, ObjectMapper objectMapper) throws IOException {
        Subject subject = SecurityUtils.getSubject();
        StatusCode code = subject.getPrincipal() == null ? StatusCode.Unauthenticated : StatusCode.Unauthorized;
        ResponseEntity entity = code.build("");
        response.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(response.getWriter(), entity);
    }
}
