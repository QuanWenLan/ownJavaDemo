package org.quange.springframework;

import org.openjdk.jol.info.ClassLayout;
import org.quange.springframework.bean.IUserService;
import org.quange.springframework.bean.UserService;
import cn.hutool.core.io.IoUtil;
import org.junit.Before;
import org.junit.Test;
import org.quange.springframework.bean.UserServiceByAware;
import org.quange.springframework.beans.BeansException;
import org.quange.springframework.beans.factory.support.CglibSubclassingInstantiationStrategy;
import org.quange.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.quange.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.quange.springframework.common.MyBeanFactoryPostProcessor;
import org.quange.springframework.common.MyBeanPostProcessor;
import org.quange.springframework.context.support.ClassPathXmlApplicationContext;
import org.quange.springframework.core.io.DefaultResourceLoader;
import org.quange.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Lan
 * @createTime 2023-08-22  15:19
 * 测试从xml文件中加载类
 **/
public class ApiTest2 {
    private DefaultResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. BeanDefinition 加载完成 & Bean实例化之前，修改 BeanDefinition 的属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 4. Bean实例化之后，修改 Bean 属性信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // 5. 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    @Test
    public void test_xml() throws BeansException {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.setInstantiationStrategy(new CglibSubclassingInstantiationStrategy());
        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        int count = reader.loadBeanDefinitions("classpath:spring.xml");
        System.out.println("count = " + count);

        // 3. 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    /**
     * 从统一入口加载
     */
    @Test
    public void test_xml2() throws BeansException {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");

        // 3. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }


    /**
     * 从统一入口加载，添加了init初始化方法和销毁方法
     */
    @Test
    public void test_xml3() throws BeansException {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor-init.xml");
        // 注册了一个钩子函数
        applicationContext.registerShutdownHook();

        // 3. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    /**
     * 从统一入口加载，添加了init初始化方法和销毁方法，添加aware接口
     */
    @Test
    public void test_xml3_aware() throws BeansException {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-aware.xml");
        // 注册了一个钩子函数
        applicationContext.registerShutdownHook();

        // 3. 获取Bean对象调用方法
        UserServiceByAware userServiceByAware = applicationContext.getBean("userServiceByAware", UserServiceByAware.class);
        String result = userServiceByAware.queryUserInfo();
        System.out.println("测试结果：" + result);
        System.out.println("ApplicationContext：" + userServiceByAware.getApplicationContext());
        System.out.println("BeanFactory：" + userServiceByAware.getBeanFactory());
    }

    @Test
    public void test_xml4_prototype() throws BeansException {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-factory-bean.xml");
        // 注册了一个钩子函数
        applicationContext.registerShutdownHook();

        // 3. 获取Bean对象调用方法
        // 2. 获取Bean对象调用方法
        IUserService userService01 = applicationContext.getBean("userService", IUserService.class);
        IUserService userService02 = applicationContext.getBean("userService", IUserService.class);

        // 3. 配置 scope="prototype/singleton"
        System.out.println(userService01);
        System.out.println(userService02);

        // 4. 打印十六进制哈希
        System.out.println(userService01 + " 十六进制哈希：" + Integer.toHexString(userService01.hashCode()));
        System.out.println(userService02 + " 十六进制哈希：" + Integer.toHexString(userService02.hashCode()));
        System.out.println(ClassLayout.parseInstance(userService01).toPrintable());
    }

    @Test
    public void test_factory_bean() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-factory-bean.xml");
        applicationContext.registerShutdownHook();
        // 2. 调用代理方法
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

    @Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_url() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }
}
