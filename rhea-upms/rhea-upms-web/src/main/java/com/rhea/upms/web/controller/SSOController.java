package com.rhea.upms.web.controller;

import com.rhea.common.base.BaseController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/sso")
public class SSOController extends BaseController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        return null;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout() {
        // shiro退出登录
        SecurityUtils.getSubject().logout();
    }
}
