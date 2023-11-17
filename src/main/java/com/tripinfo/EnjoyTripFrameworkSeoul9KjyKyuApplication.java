package com.tripinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class EnjoyTripFrameworkSeoul9KjyKyuApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnjoyTripFrameworkSeoul9KjyKyuApplication.class, args);
    }
}
