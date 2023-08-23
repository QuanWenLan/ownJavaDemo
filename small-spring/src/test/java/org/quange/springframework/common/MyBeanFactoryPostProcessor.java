package org.quange.springframework.common;

import org.quange.springframework.beans.BeansException;
import org.quange.springframework.beans.PropertyValue;
import org.quange.springframework.beans.PropertyValues;
import org.quange.springframework.beans.factory.ConfigurableListableBeanFactory;
import org.quange.springframework.beans.factory.config.BeanDefinition;
import org.quange.springframework.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }

}
