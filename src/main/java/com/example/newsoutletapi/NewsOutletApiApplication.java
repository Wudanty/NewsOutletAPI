package com.example.newsoutletapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class NewsOutletApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsOutletApiApplication.class, args);
    }

}
