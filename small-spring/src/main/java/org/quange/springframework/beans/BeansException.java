package org.quange.springframework.beans;

/**
 * @author Lan
 * @createTime 2023-08-21  10:36
 **/
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
