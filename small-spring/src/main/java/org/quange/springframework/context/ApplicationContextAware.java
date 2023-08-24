package org.quange.springframework.context;

import org.quange.springframework.beans.BeansException;
import org.quange.springframework.beans.factory.Aware;

/**
 * @author Lan
 * @createTime 2023-08-24  11:46
 * Interface to be implemented by any object that wishes to be notified
 * of the {@link ApplicationContext} that it runs in.
 *
 * 实现此接口，既能感知到所属的 ApplicationContext
 **/
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
