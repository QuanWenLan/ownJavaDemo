package springtest.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

/**
 * @author Vin lan
 * @className MyInstantiationAwareBeanPostProcessor
 * @description
 * @createTime 2021-08-26  09:51
 **/
public class MyInstantiationAwareBeanPostProcessor  implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + " =====");
        return null;
    }
}
