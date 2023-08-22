package org.quange.springframework.beans.factory.support;

import org.quange.springframework.beans.BeansException;
import org.quange.springframework.beans.factory.BeanFactory;
import org.quange.springframework.beans.factory.config.BeanDefinition;

/**
 * @author Lan
 * @createTime 2023-08-18  17:20
 * 抽象类定义模板方法
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(String name, Object... args) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T)bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T)createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition,  Object[] args) throws BeansException;
}
