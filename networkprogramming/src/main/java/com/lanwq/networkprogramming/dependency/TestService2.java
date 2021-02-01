package com.lanwq.networkprogramming.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vin lan
 * @className TestService2
 * @description TODO
 * @createTime 2021-02-01  09:10
 **/
@Service
public class TestService2 {
//    @Autowired
    private TestService1 testService1;   // 属性注入

    @Autowired
    public TestService2(TestService1 testService1) {   // 构造注入
        System.out.println("构造注入 testService1");
        this.testService1 = testService1;
    }

    public TestService2() {
        System.out.println("空构造方法 TestService2");
    }

//    @Autowired
    public void setTestService1(TestService1 testService1) { // setter 注入
        System.out.println("setTestService1 方法");
        this.testService1 = testService1;
    }
}
