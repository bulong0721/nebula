package io.nebula.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/4/22
 */
@Slf4j
@RestController
@RequestMapping("/actuator")
public class ActuatorController {

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public String version() {
        return System.getProperty("online.version", "unknown");
    }
}
