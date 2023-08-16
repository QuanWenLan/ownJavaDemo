package com.quange.mvcboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class MvcBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcBootApplication.class, args);
    }

}
