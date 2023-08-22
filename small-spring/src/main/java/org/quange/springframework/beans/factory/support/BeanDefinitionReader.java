package org.quange.springframework.beans.factory.support;

import org.quange.springframework.beans.BeansException;
import org.quange.springframework.core.io.Resource;
import org.quange.springframework.core.io.ResourceLoader;

/**
 * @author Lan
 * @createTime 2023-08-22  14:00
 **/
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

//    ClassLoader getBeanClassLoader();

    int loadBeanDefinitions(Resource resource) throws BeansException;

    int loadBeanDefinitions(Resource... resources) throws BeansException;

    int loadBeanDefinitions(String location) throws BeansException;

    int loadBeanDefinitions(String... locations) throws BeansException;
}
