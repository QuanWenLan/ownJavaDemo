import bean.UserService;
import org.junit.Test;
import org.quange.springframework.beans.factory.factory.BeanDefinition;
import org.quange.springframework.beans.factory.BeanFactory;

/**
 * @author Lan
 * @createTime 2023-08-18  17:05
 **/
public class ApiTest {

    @Test
    public void testBeanFactory() {
        // 1.初始化 BeanFactory
        BeanFactory beanFactory = new BeanFactory();

        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
