package io.nebula.web.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/01/01
 */
public class DirectUserFilter extends UserFilter {
    private final ObjectMapper objectMapper;

    public DirectUserFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        DirectUtil.onAccessDenied(request, response, objectMapper);
        return false;
    }
}
