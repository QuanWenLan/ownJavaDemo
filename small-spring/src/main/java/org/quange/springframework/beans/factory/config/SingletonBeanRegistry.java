package org.quange.springframework.beans.factory.config;

/**
 * @author Lan
 * @createTime 2023-08-18  17:25
 **/
public interface SingletonBeanRegistry {

    /**
     * 获取单例对象的接口
     * @param beanName bean name
     * @return bean
     */
    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObject);
}
