package org.quange.springframework.beans.factory;

import org.quange.springframework.beans.BeansException;

/**
 * @author Lan
 * @createTime 2023-08-24  11:32
 * Interface to be implemented by beans that wish to be aware of their
 * owning {@link BeanFactory}.
 *
 * <p>For example, beans can look up collaborating beans via the factory
 * (Dependency Lookup). Note that most beans will choose to receive references
 * to collaborating beans via corresponding bean properties or constructor
 * arguments (Dependency Injection).
 **/
public interface BeanFactoryAware extends Aware {

    /**
     * Callback that supplies the owning factory to a bean instance.
     * <p>Invoked after the population of normal bean properties
     * but before an initialization callback such as
     * {@link InitializingBean#afterPropertiesSet()} or a custom init-method.
     * @param beanFactory owning BeanFactory (never {@code null}).
     * The bean can immediately call methods on the factory.
     * @throws BeansException in case of initialization errors
     * @see BeanInitializationException
     */
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
