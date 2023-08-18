package org.quange.springframework.beans.factory.support;

import org.quange.springframework.beans.factory.BeanFactory;
import org.quange.springframework.beans.factory.factory.BeanDefinition;

/**
 * @author Lan
 * @createTime 2023-08-18  17:20
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.putIfAbsent(beanName, beanDefinition);
    }

    @Override
    public Object getBean(String beanName) {
        return beanDefinitionMap.get(beanName).getBean();
    }
}
