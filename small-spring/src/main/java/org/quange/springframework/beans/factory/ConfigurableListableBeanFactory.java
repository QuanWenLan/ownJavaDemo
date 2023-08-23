package org.quange.springframework.beans.factory;

import org.quange.springframework.beans.BeansException;
import org.quange.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.quange.springframework.beans.factory.config.BeanDefinition;
import org.quange.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author Lan
 * @createTime 2023-08-22  14:17
 *
 * Configuration interface to be implemented by most listable bean factories.
 * In addition to {@link ConfigurableBeanFactory}, it provides facilities to
 * analyze and modify bean definitions, and to pre-instantiate singletons.
 *
 * 配置接口，由大多数可列出的bean工厂实现。除了{@link ConfigurableBeanFactory}之外，
 * 它还提供了分析和修改bean定义以及预实例化单例的工具。
 *
 * <p>This subinterface of {@link BeanFactory} is not meant to be used in normal application code: Stick to
 * {@link BeanFactory} or {@link ListableBeanFactory} for typical
 * use cases. This interface is just meant to allow for framework-internal
 * plug'n'play even when needing access to bean factory configuration methods.
 *
 * 这个子接口并不打算在正常的应用程序代码中使用:对于典型的用例，坚持使用{@link BeanFactory}或{@link ListableBeanFactory}。
 * 这个接口只是为了允许框架内部即插即用，即使在需要访问bean工厂配置方法时也是如此。
 **/
public interface ConfigurableListableBeanFactory extends ListableBeanFactory,  AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;
}
