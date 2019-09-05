package io.nebula.web.controller;

import io.nebula.kernel.exchange.ResponseEntity;
import io.nebula.kernel.exchange.StatusCode;
import io.nebula.kernel.security.IUser;
import io.nebula.kernel.service.ServiceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/12/19
 */
@Slf4j
@RestController
@RequestMapping("/")
public class HomeController {


    /**
     * 获取当前用户
     *
     * @return
     */
    @RequestMapping(value = "/currentUser")
    public ResponseEntity<Object> currentUser() {
        IUser user = ServiceContext.currentUser();
        return StatusCode.OK.build(user.summary());
    }
}
