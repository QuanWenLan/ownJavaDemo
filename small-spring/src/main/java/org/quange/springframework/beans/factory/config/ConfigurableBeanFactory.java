package org.quange.springframework.beans.factory.config;

import org.quange.springframework.beans.factory.HierarchicalBeanFactory;
import org.quange.springframework.utils.StringValueResolver;

/**
 * @author Lan
 * @createTime 2023-08-22  14:30
 * <p>
 * Configuration interface to be implemented by most bean factories. Provides
 * facilities to configure a bean factory, in addition to the bean factory
 * client methods in the {@link org.quange.springframework.beans.factory.BeanFactory}
 * interface.
 * <p>This bean factory interface is not meant to be used in normal application
 * code: Stick to {@link org.quange.springframework.beans.factory.BeanFactory} or
 * {@link org.quange.springframework.beans.factory.ListableBeanFactory} for typical
 * needs. This extended interface is just meant to allow for framework-internal
 * plug'n'play and for special access to bean factory configuration methods.
 * 这个bean工厂接口不打算在普通的应用程序代码中使用:坚持使用{@link org.quange.springframework.beans.factory.BeanFactory。
 * 或者{@link org.quange.springframework.beans.factory.ListableBeanFactory}用于典型需求。这个扩展的接口只是为了允许框架内部的即插即用和对bean工厂配置方法的特殊访问。
 **/
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();

    /**
     * Add a String resolver for embedded values such as annotation attributes.
     * @param valueResolver the String resolver to apply to embedded values
     * @since 3.0
     */
    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    /**
     * Resolve the given embedded value, e.g. an annotation attribute.
     * @param value the value to resolve
     * @return the resolved value (may be the original value as-is)
     * @since 3.0
     */
    String resolveEmbeddedValue(String value);
}
