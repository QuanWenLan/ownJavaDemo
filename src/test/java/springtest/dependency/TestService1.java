package springtest.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Vin lan
 * @className TestService1
 * @description TODO spring循环依赖
 * @createTime 2021-02-01  09:10
 **/
@Service
public class TestService1 {
//    @Autowired
    private TestService2 testService2;   // 属性注入

    @Autowired
    public TestService1(TestService2 testService2) {   // 构造注入
        System.out.println("TestService1 构造注入 testService2");
        this.testService2 = testService2;
    }

    public TestService1() {
        System.out.println("空构造方法 TestService1");
    }

    @Async
    public void test1() {
        System.out.println("Async");
    }

    @Autowired
    public void setTestService2(TestService2 testService2) {  // 设值注入
        System.out.println("setTestService2 方法");
        this.testService2 = testService2;
    }
}
