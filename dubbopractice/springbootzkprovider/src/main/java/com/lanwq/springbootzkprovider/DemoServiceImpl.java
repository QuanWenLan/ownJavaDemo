package com.lanwq.springbootzkprovider;

import com.lanwq.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author Lan
 * @createTime 2023-10-08  10:15
 **/
@Slf4j
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        log.info("I'm springboot-zk-provider, Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "I'm springboot-zk-provider, Hello " + name + ", response from provider: " + RpcContext.getContext().getLocalAddress();
    }
}
