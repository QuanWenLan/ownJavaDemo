package com.lanwq.springbootzkprovider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Lan
 * @createTime 2023-10-08  10:16
 **/
@SpringBootApplication
@EnableDubbo
public class SpringBootZKProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootZKProviderApplication.class, args);
    }
}
