package org.quange.springframework.beans.factory;

import org.quange.springframework.beans.BeansException;

/**
 * @author Lan
 * @createTime 2023-08-18  16:59
 * 代表 bean 对象的工厂，只是用来获取bean，担各自的职责
 **/
public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;
}
