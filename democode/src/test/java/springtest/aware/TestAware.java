package springtest.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @author Vin lan
 * @className TestAware
 * @description 实现相关的 Aware 接口会有想相对应的功能
 * @createTime 2021-08-24  17:23
 **/
public class TestAware implements BeanFactoryAware {
    private BeanFactory beanFactory;

    // 声明 bean 的时候 spring 会自动注入 BeanFactory
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public void testAware() {
        // 通过 hello 这个 bean 从 beanFactory 获取实例
        Hello hello = (Hello) beanFactory.getBean("hello");
        hello.say();
    }
}
