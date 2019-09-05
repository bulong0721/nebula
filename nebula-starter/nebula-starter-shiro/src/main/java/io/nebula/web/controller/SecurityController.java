package io.nebula.web.controller;

import io.nebula.kernel.exchange.ResponseEntity;
import io.nebula.kernel.exchange.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/12/19
 */
@Slf4j
@RestController
@RequestMapping("/")
public class SecurityController {

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        SecurityUtils.getSubject().login(token);
        return StatusCode.OK.build(username);
    }

    /**
     * 登出
     *
     * @return
     */
    @RequestMapping(value = "/logout")
    public ResponseEntity<String> logout() {
        SecurityUtils.getSubject().logout();
        return StatusCode.OK.build();
    }

}
