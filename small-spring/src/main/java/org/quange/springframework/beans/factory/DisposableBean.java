package org.quange.springframework.beans.factory;

/**
 * @author Lan
 * @createTime 2023-08-24  10:39
 * bean 销毁时调用
 **/
public interface DisposableBean {
    void destroy() throws Exception;
}
