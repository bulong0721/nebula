package com.rhea.upms.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 登录信息拦截器
 */
public class UpmsInterceptor extends HandlerInterceptorAdapter {

    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }

    @Override public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
        Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
