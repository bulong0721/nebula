package io.nebula.web.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.nebula.kernel.exchange.ResponseEntity;
import io.nebula.kernel.exchange.StatusCode;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author 徐步龙
 * @version V1.0 created at: 2018/12/22
 */
public class DirectLogoutFilter extends LogoutFilter {
    private ObjectMapper objectMapper;

    public DirectLogoutFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void issueRedirect(ServletRequest request, ServletResponse response, String redirectUrl) throws Exception {
        ResponseEntity entity = StatusCode.OK.build("");
        response.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(response.getWriter(), entity);
    }
}
