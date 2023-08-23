package org.quange.springframework.context.support;

import org.quange.springframework.beans.BeansException;
import org.quange.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.quange.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author Lan
 * @createTime 2023-08-23  09:26
 **/
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
