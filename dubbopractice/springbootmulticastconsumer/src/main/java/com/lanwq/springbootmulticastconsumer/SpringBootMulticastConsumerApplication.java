package com.lanwq.springbootmulticastconsumer;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@DubboComponentScan
public class SpringBootMulticastConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMulticastConsumerApplication.class, args);
    }
}
