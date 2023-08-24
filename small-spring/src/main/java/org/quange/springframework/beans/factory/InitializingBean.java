package org.quange.springframework.beans.factory;

/**
 * @author Lan
 * @createTime 2023-08-24  10:38
 * bean 初始化时调用
 **/
public interface InitializingBean {
    /**
     * bean 处理了属性填充后使用
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
