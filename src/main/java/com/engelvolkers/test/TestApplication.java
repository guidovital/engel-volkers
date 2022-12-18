package com.engelvolkers.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableWebMvc
public class TestApplication {

    public static void main(String[] args) {
        log.info("Application is starting...");
        SpringApplication.run(TestApplication.class, args);
    }

}
