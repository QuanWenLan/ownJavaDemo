package org.quange.springframework.beans.factory.config;

import org.quange.springframework.beans.BeansException;
import org.quange.springframework.beans.factory.BeanFactory;

/**
 * @author Lan
 * @createTime 2023-08-22  14:31
 * Extension of the {@link BeanFactory} interface to be implemented by bean factories that are capable of
 * autowiring, provided that they want to expose this functionality for existing bean instances.
 **/
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;

}
