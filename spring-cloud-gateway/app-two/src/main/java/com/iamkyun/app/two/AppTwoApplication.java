package com.iamkyun.app.two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class AppTwoApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppTwoApplication.class);
    }

    @RestController
    @RequestMapping
    static class TwoController {
        @GetMapping("/hello")
        public String sayHello() {
            return "Hello Two";
        }
    }
}
