package org.quange.springframework;

import org.junit.Test;
import org.quange.springframework.annotation.IUserServiceAnnotated;
import org.quange.springframework.bean.IUserService;
import org.quange.springframework.beans.BeansException;
import org.quange.springframework.beans.factory.config.BeanPostProcessor;
import org.quange.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lan
 * @createTime 2023-08-29  11:52
 **/
public class ApiTestAnnotated {
    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");
        IUserServiceAnnotated userService = applicationContext.getBean("userService", IUserServiceAnnotated.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

    @Test
    public void test_property() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-property.xml");
        IUserServiceAnnotated userService = applicationContext.getBean("userService", IUserServiceAnnotated.class);
        System.out.println("测试结果：" + userService);
    }

    @Test
    public void test_beanPost(){

        BeanPostProcessor beanPostProcessor = new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                return null;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                return null;
            }
        };

        List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();
        beanPostProcessors.add(beanPostProcessor);
        beanPostProcessors.add(beanPostProcessor);
        beanPostProcessors.remove(beanPostProcessor);

        System.out.println(beanPostProcessors.size());
    }
}
