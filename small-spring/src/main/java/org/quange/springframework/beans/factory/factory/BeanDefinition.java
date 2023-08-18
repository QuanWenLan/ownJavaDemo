package org.quange.springframework.beans.factory.factory;

/**
 * @author Lan
 * @createTime 2023-08-18  16:59
 * 用于定义bean的实例化信息
 **/
public class BeanDefinition {
    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
