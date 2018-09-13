package com.rhea.schedule.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public void sayHello(String name) {
        System.out.println("say hello to " + name);
    }
}
