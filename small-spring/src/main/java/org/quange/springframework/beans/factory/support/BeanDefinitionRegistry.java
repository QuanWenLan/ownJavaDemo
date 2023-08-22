package org.quange.springframework.beans.factory.support;

import org.quange.springframework.beans.BeansException;
import org.quange.springframework.beans.factory.config.BeanDefinition;

/**
 * @author Lan
 * @createTime 2023-08-21  10:44
 * 注册bean的接口
 **/
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 使用Bean名称查询BeanDefinition
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 判断是否包含指定名称的BeanDefinition
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * Return the names of all beans defined in this registry.
     *
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
