package com.lanwq.springbootmulticastprovider;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//https://xinchen.blog.csdn.net/article/details/109142783
@SpringBootApplication
@EnableDubbo
@DubboComponentScan
public class SpringBootMulticastProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMulticastProviderApplication.class, args);
    }
}
