package io.nebula.web.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/01/01
 */
public class DirectPermissionFilter extends PermissionsAuthorizationFilter {
    private final ObjectMapper objectMapper;

    public DirectPermissionFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        DirectUtil.onAccessDenied(request, response, objectMapper);
        return false;
    }
}
