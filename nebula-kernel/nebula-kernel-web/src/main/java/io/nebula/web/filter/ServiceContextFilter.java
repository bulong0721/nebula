package io.nebula.web.filter;

import io.nebula.kernel.Constants;
import io.nebula.kernel.security.IUser;
import io.nebula.kernel.service.ServiceContext;
import io.nebula.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 徐步龙
 * @version V1.0 created at: 2018/12/20
 */
@Slf4j
public class ServiceContextFilter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        IUser currentUser = SessionUtil.getAttribute(Constants.SESSION_USER, request);
        ServiceContext ctx = ServiceContext.instance();
        ctx.setCurrentUser(currentUser);
        ctx.setRequest(request);
        ctx.setResponse(response);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ServiceContext.instance().clear();
    }
}
