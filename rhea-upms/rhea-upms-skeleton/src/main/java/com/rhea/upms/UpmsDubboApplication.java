package com.rhea.upms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
public class UpmsDubboApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpmsDubboApplication.class);
    }
}
