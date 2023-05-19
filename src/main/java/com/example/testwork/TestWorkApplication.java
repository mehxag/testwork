package com.example.testwork;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
public class TestWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestWorkApplication.class, args);
    }

}