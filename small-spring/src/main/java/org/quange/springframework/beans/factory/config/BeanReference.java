package org.quange.springframework.beans.factory.config;

/**
 * @author Lan
 * @createTime 2023-08-22  11:42
 **/
public class BeanReference {
    private String beanName;

    public BeanReference() {
    }

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
