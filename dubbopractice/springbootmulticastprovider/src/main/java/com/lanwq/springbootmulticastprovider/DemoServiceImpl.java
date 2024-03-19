package com.lanwq.springbootmulticastprovider;

import com.lanwq.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.config.annotation.Service;

/**
 * @Service 注解是dubbo的注解，不是spring的，可以对其设置一个调用的超时时间：timeout = 2000。
 * 还可以设置其他的一些属性
 * 服务接口实现类配置：
 * @Service(interfaceClass = YourService.class)：指定服务接口。
 * 超时配置：
 * @Service(timeout = 3000)：设置服务的超时时间（毫秒）。
 * 版本配置：
 * @Service(version = "1.0")：指定服务的版本号。
 * 集群配置：
 * @Service(cluster = "failfast")：设置集群容错模式，例如 failfast、failsafe、failover 等。
 */
@Slf4j
@Service(timeout = 2000, retries = 2)
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        log.info("Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello " + name + ", response from provider: " + RpcContext.getContext().getLocalAddress();
    }
}
