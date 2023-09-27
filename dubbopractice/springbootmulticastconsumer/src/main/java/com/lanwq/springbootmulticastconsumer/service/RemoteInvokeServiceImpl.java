package com.lanwq.springbootmulticastconsumer.service;

import com.lanwq.service.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class RemoteInvokeServiceImpl {

    // , parameters = {"unicast", "false"} 需要设置这个参数，在配置文件里设置没有用！！！麻痹的
    @Reference(timeout = 2000, parameters = {"unicast", "false"})
    private DemoService demoService;

    public String sayHello(String name) {
        return "from dubbo remote (multicast mode) : " + demoService.sayHello(name);
    }
}
