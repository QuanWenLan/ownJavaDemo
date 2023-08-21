package org.quange.springframework.beans.factory.support;

import org.quange.springframework.beans.factory.config.BeanDefinition;

/**
 * @author Lan
 * @createTime 2023-08-21  10:44
 * 注册bean的接口
 **/
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
