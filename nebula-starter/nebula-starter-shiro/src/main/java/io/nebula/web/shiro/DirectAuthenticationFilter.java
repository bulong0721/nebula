package io.nebula.web.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author 徐步龙
 * @version V1.0 created at: 2018/12/22
 */
public class DirectAuthenticationFilter extends PassThruAuthenticationFilter {
    private final ObjectMapper objectMapper;

    public DirectAuthenticationFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        if (isLoginRequest(servletRequest, servletResponse)) {
            return true;
        } else {
            DirectUtil.onAccessDenied(servletRequest, servletResponse, objectMapper);
            return false;
        }
    }
}
