package bean;

import cn.hutool.core.io.IoUtil;
import org.junit.Before;
import org.junit.Test;
import org.quange.springframework.beans.BeansException;
import org.quange.springframework.beans.factory.support.CglibSubclassingInstantiationStrategy;
import org.quange.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.quange.springframework.beans.factory.xml.XmlBeanDefinitionReader;
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
