import bean.UserService;
import org.junit.Test;
import org.quange.springframework.beans.BeansException;
import org.quange.springframework.beans.factory.factory.BeanDefinition;
import org.quange.springframework.beans.factory.BeanFactory;
import org.quange.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author Lan
 * @createTime 2023-08-18  17:05
 **/
public class ApiTest {

    @Test
    public void testBeanFactory() throws BeansException {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        // 4. 第二次获取bean, from SingletonObjects
        UserService userServiceSingleton = (UserService) beanFactory.getBean("userService");
        userServiceSingleton.queryUserInfo();
    }
}
