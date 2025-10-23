package com.example.apiredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApiRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiRedisApplication.class, args);
    }

}
