package com.casumo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class CoffeeshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeshopApplication.class, args);
    }
}
