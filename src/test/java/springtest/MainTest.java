package springtest;

import org.junit.Test;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import springtest.annotation.AnnotationTest;
import springtest.aop.MyInvocationHandler;
import springtest.aop.TestBean;
import springtest.aop.UserService;
import springtest.aop.UserServiceImpl;
import springtest.aware.TestAware;
import springtest.beanpostprocessor.MyInstantiationAwareBeanPostProcessor;
import springtest.listener.OrderEvent;
import springtest.listener.TestEvent;
import springtest.lookup.bean.GetBeanTest;
import springtest.property.User;
import springtest.replace.bean.TestChangeMethod;

/**
 * @author Vin lan
 * @className MainTest
 * @description
 * @createTime 2021-08-17  11:43
 **/
public class MainTest {
    public static void main(String[] args) {
        System.out.println(MainTest.class.getResource("/").getPath());
        ClassPathXmlApplicationContext bf = new ClassPathXmlApplicationContext("classpath:lookupTest.xml");
        GetBeanTest test = (GetBeanTest) bf.getBean("getBeanTest");
        test.showMe();

        // test replace method

        /*ClassPathXmlApplicationContext bf2 = new ClassPathXmlApplicationContext("classpath:replaceMethod.xml");
        TestChangeMethod testChangeMethod = (TestChangeMethod) bf2.getBean("testChangeMethod");
        testChangeMethod.changeMe();*/

//        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("lookupTest.xml"));
    }

    @Test
    public void testFactoryBean() {
        ClassPathXmlApplicationContext bf2 = new ClassPathXmlApplicationContext("classpath:test.xml");
        Car car = (Car) bf2.getBean("car");
        System.out.println(car.getBrand());
    }

    // 测试 构造器 循环依赖
    @Test(expected = BeanCurrentlyInCreationException.class)
    public void testCycleDependency() throws Throwable {
        try {
            new ClassPathXmlApplicationContext("classpath:cycleDependency.xml");
        } catch (Exception e) {
            // 因为要在创建 testC 时抛出
            Throwable el = e.getCause().getCause().getCause();
            throw el;
        }
    }

    // 测试 setter 循环依赖
    @Test
    public void testSetterCycleDependency() throws Throwable {
        try {
            new ClassPathXmlApplicationContext("classpath:cycleSetterDependency.xml");
        } catch (Exception e) {
            // 因为要在创建 testC 时抛出
            Throwable el = e.getCause().getCause().getCause();
            throw el;
        }
    }

    @Test
    public void testAware() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:test.xml");
        /*TestAware testAware = (TestAware) context.getBean("testAware");
        testAware.testAware();*/
        // 输出 hello

        MyInstantiationAwareBeanPostProcessor myBeanPostProcessor = (MyInstantiationAwareBeanPostProcessor) context.getBean("myBeanPostProcessor");
    }

    @Test
    public void testProperty() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:testProperty.xml");
        User u = (User) context.getBean("user");
        System.out.println(u);

    }

    // 测试监听事件
    @Test
    public void testListener() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:listener.xml");
        TestEvent event = new TestEvent("hello", "msg");
        context.publishEvent(event);
        for (int i = 0; i < 5; i++) {
            String orderCode = "test_order_" + i;
            String goodsCode = "test_order_" + i;
            String redPacketCode = null;
            if (i % 2 == 0) {
                //偶数时使用红包
                redPacketCode = "test_order_" + i;
            }
            OrderEvent orderEvent = new OrderEvent(context, orderCode, goodsCode, redPacketCode);
            /** 3. ApplicationContext实现了ApplicationEventPublisher接口,所以可以直接通过ApplicationContext来发送事件*/
            context.publishEvent(orderEvent);
        }
    }

    // 测试AOP
    @Test
    public void testAop() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:testAop.xml");
        TestBean testBean = (TestBean) context.getBean("test");
        testBean.test(new TestBean.Pa());
        // 这种方式创建出来的不是 代理 对象，而是普通的 new 出来的对象，不会触发 增强方法.上面使用 getBean 方法的才是 spring 创建的代理对象
        System.out.println("**************");
        TestBean.createInstance().test(new TestBean.Pa());
    }

    // 测试 代理
    @Test
    public void testProxy() {
        // 实例化目标对象
        UserService userService = new UserServiceImpl();
        // 实例化 InvocationHandler
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(userService);
        // 根据目标对象生成代理对象
        UserService proxy = (UserService) myInvocationHandler.getProxy();
        // 调用代理对象的方法
        proxy.add();
        proxy.add22();
    }

    @Test
    public void testSwitch() {
        String a = null;
        method(a); // 报错, null 异常
    }

    public void method(String param) {
        switch (param) {
            case "sh":
                System.out.println("sh");
                break;
            case "null":
                System.out.println("null");
                break;
            default:
                System.out.println("default");
        }
    }

    /**
     * 事务源码分析，测试
     */
    @Test
    public void testTx() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:testTx.xml");
        springtest.tx.UserService userService = (springtest.tx.UserService) context.getBean("userService");
        springtest.tx.User user = new springtest.tx.User();
        user.setEmail("1111@163.com");
        user.setName("zhang san");
        user.setUserId(2);
        userService.save(user);
    }


    // 测试注解开发
    @Test
    public void testAnnotation() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:testAnnotation.xml");
        AnnotationTest testBean = (AnnotationTest) context.getBean("annotationTest");
        testBean.print();
    }
}
