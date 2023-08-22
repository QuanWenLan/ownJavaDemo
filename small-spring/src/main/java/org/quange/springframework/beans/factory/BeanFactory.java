package org.quange.springframework.beans.factory;

import org.quange.springframework.beans.BeansException;

/**
 * @author Lan
 * @createTime 2023-08-18  16:59
 * 访问spring的bean容器的根接口。这是bean容器的基本客户端视图。
 **/
public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
