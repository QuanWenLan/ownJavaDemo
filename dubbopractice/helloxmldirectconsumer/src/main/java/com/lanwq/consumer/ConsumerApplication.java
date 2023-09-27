package com.lanwq.consumer;

import com.lanwq.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerApplication {
    /**
     * In order to make sure multicast registry works, need to specify '-Djava.net.preferIPv4Stack=true' before
     * launch the application
     */
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-consumer.xml");
        context.start();
        DemoService demoService = context.getBean("demoService", DemoService.class);
        String hello = demoService.sayHello("world1");
        System.out.println("result: " + hello);
    }
    /**
     * 出现的问题：https://blog.csdn.net/OldFat_/article/details/90578866
     *
     * Failed to invoke the method sayHello in the service com.lanwq.service.DemoService.
     * No provider available for the service com.lanwq.service.DemoService from registry 224.5.6.7:1234 on the consumer 192.168.2.117 using the dubbo version 2.7.6.
     * Please check if the providers have been started and registered.
     *
     * consumer端xml配置
     * https://github.com/apache/dubbo/issues/4136
     * https://github.com/apache/dubbo/issues/3401
     * 最后提交的配置文件
     * https://github.com/apache/dubbo-website/pull/656
     *
     * 在局域网中使用multicast注册中心出现异常 #5647：https://github.com/apache/dubbo/issues/5647
     *
     */
}
