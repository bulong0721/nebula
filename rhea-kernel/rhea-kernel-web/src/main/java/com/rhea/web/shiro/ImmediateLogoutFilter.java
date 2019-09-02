package com.rhea.web.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhea.kernel.exchange.ResponseEntity;
import com.rhea.kernel.exchange.StatusCode;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author xubulong
 * @version V1.0
 */
public class ImmediateLogoutFilter extends LogoutFilter {
    private ObjectMapper objectMapper;

    public ImmediateLogoutFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void issueRedirect(ServletRequest request, ServletResponse response, String redirectUrl) throws Exception {
        ResponseEntity entity = StatusCode.OK.build("");
        response.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(response.getWriter(), entity);
    }
}
