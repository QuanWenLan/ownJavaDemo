package org.quange.springframework.beans.factory.config;

import org.quange.springframework.beans.factory.BeanFactory;
import org.quange.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * @author Lan
 * @createTime 2023-08-22  14:30
 *
 * Configuration interface to be implemented by most bean factories. Provides
 * facilities to configure a bean factory, in addition to the bean factory
 * client methods in the {@link BeanFactory}
 * interface.
 **/
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";
}
