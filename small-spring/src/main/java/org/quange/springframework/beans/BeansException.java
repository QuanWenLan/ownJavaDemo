package org.quange.springframework.beans;

/**
 * @author Lan
 * @createTime 2023-08-21  10:36
 **/
public class BeansException extends Exception {
    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BeansException(String msg) {
        super(msg);
    }
}
