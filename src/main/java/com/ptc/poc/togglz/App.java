package com.ptc.poc.togglz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableAutoConfiguration
@SpringBootApplication
@EnableMongoAuditing
public class App {
    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }
}
