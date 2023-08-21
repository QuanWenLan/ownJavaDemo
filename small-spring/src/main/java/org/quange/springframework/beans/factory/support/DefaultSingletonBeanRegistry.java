package org.quange.springframework.beans.factory.support;

import org.quange.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lan
 * @createTime 2023-08-18  17:26
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 存储bean的单例对象
     */
    protected Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return this.singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        this.singletonObjects.put(beanName, singletonObject);
    }
}
