package org.quange.springframework.context;

import org.quange.springframework.beans.BeansException;

/**
 * @author Lan
 * @createTime 2023-08-23  09:25
 * SPI interface to be implemented by most if not all application contexts.
 * Provides facilities to configure an application context in addition
 * to the application context client methods in the
 * {@link org.quange.springframework.context.ApplicationContext} interface.
 *
 * <p>Configuration and lifecycle methods are encapsulated here to avoid
 * making them obvious to ApplicationContext client code. The present
 * methods should only be used by startup and shutdown code.
 **/
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     * Load or refresh the persistent representation of the configuration, which
     * might be from Java-based configuration, an XML file, a properties file, a
     * relational database schema, or some other format.
     * <p>As this is a startup method, it should destroy already created singletons
     * if it fails, to avoid dangling resources. In other words, after invocation
     * of this method, either all or no singletons at all should be instantiated.
     * @throws BeansException if the bean factory could not be initialized
     * @throws IllegalStateException if already initialized and multiple refresh
     * attempts are not supported
     */
    void refresh() throws BeansException;
}
