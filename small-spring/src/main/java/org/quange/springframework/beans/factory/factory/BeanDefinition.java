package org.quange.springframework.beans.factory.factory;

/**
 * @author Lan
 * @createTime 2023-08-18  16:59
 * 用于定义bean的实例化信息
 **/
public class BeanDefinition {
    /**
     * class 对象的话，可以给容器来创建实例
     */
    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
