package com.rhea.web.controller;

import com.rhea.kernel.exchange.ResponseEntity;
import com.rhea.kernel.exchange.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xubulong
 * @version V1.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RestController
@RequestMapping("/")
public class HomeController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    ResponseEntity<String> login(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        SecurityUtils.getSubject().login(token);
        return StatusCode.OK.build(username);
    }

    @RequestMapping(value = "/logout")
    ResponseEntity<String> logout() {
        SecurityUtils.getSubject().logout();
        return StatusCode.OK.build();
    }

    @RequestMapping(value = "/currentUser")
    ResponseEntity<String> currentUser() {
        String principal = (String) SecurityUtils.getSubject().getPrincipal();
        return StatusCode.OK.build(principal);
    }
}
