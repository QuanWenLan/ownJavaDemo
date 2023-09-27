package com.lanwq.provider.service.impl;

import com.lanwq.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.RpcContext;

@Slf4j
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
