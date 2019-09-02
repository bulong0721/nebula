package com.rhea.web.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhea.kernel.exchange.ResponseEntity;
import com.rhea.kernel.exchange.StatusCode;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author xubulong
 * @version V1.0
 */
public class ImmediateAuthenticationFilter extends PassThruAuthenticationFilter {
    private ObjectMapper objectMapper;

    public ImmediateAuthenticationFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        if (isLoginRequest(servletRequest, servletResponse)) {
            return true;
        } else {
            ResponseEntity entity = StatusCode.Unauthorized.build("");
            servletResponse.setContentType("application/json;charset=utf-8");
            objectMapper.writeValue(servletResponse.getWriter(), entity);
            return false;
        }
    }
}
