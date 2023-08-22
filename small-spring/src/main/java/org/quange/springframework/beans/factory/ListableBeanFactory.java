package org.quange.springframework.beans.factory;

import org.quange.springframework.beans.BeansException;

import java.util.Map;

/**
 * @author Lan
 * @createTime 2023-08-22  14:16
 * Extension of the {@link BeanFactory} interface to be implemented by bean factories
 * that can enumerate all their bean instances, rather than attempting bean lookup
 * by name one by one as requested by clients. BeanFactory implementations that
 * preload all their bean definitions (such as XML-based factories) may implement
 * this interface.
 *
 * BeanFactory的扩展，由可以枚举所有bean实例的bean工厂实现，而不是像客户端请求的那样逐个按名称进行bean查找。
 * 预加载所有bean定义(如基于xml的工厂)的BeanFactory实现可以实现这个接口。
 **/
public interface ListableBeanFactory extends BeanFactory {
    /**
     * 按照类型返回 Bean 实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * Return the names of all beans defined in this registry.
     *
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
