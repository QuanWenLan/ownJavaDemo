package com.lanwq.springbootzkconsumer;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@DubboComponentScan
public class SpringBootZkConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootZkConsumerApplication.class, args);
    }
}
