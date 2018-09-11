package com.rhea.messaging.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String sayHello(String name) {
        return "Say hello to: " + name;
    }
}
