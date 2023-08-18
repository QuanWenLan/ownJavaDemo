package org.quange.springframework.beans.factory;

import org.quange.springframework.beans.factory.factory.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Lan
 * @createTime 2023-08-18  16:59
 * 代表 bean 对象的工厂，可以存放bean定义到map以及获取
 **/
public interface BeanFactory {
    /**
     * store bean object map
     */
    Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    Object getBean(String beanName);
}
