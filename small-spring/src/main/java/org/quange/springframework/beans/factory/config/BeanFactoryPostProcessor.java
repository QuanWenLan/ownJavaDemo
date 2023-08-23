package org.quange.springframework.beans.factory.config;

import org.quange.springframework.beans.BeansException;
import org.quange.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author Lan
 * @createTime 2023-08-23  09:24
 * 这个接口是满足于在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
 **/
public interface BeanFactoryPostProcessor {
    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
