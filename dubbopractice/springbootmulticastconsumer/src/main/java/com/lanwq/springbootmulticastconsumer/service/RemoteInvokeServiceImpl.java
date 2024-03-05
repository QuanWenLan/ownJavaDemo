package com.lanwq.springbootmulticastconsumer.service;

import com.lanwq.service.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class RemoteInvokeServiceImpl {

    /**
     * 在这边也有其他的一些属性可以进行设置
     * 服务引用配置：
     * @Reference(interfaceClass = YourService.class)：指定引用的服务接口。
     * 超时配置：
     * @Reference(timeout = 3000)：设置服务引用的超时时间（毫秒）。
     * 版本配置：
     * @Reference(version = "1.0")：指定引用的服务版本号。
     * 负载均衡配置：
     * @Reference(loadbalance = "roundrobin")：设置负载均衡策略，例如 roundrobin、random、leastactive 等。
     */
    // , parameters = {"unicast", "false"} 需要设置这个参数，在配置文件里设置没有用！！！麻痹的
    @Reference(timeout = 2000, parameters = {"unicast", "false"})
    private DemoService demoService;

    public String sayHello(String name) {
        return "from dubbo remote (multicast mode) : " + demoService.sayHello(name);
    }
}
