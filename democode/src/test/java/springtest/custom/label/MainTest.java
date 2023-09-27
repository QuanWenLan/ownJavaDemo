package springtest.custom.label;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Vin lan
 * @className MainTest
 * @description 测试自定义标签解析过程
 * @createTime 2021-08-18  14:29
 **/
public class MainTest {
    @Test
    public void test() {
        ApplicationContext bf = new ClassPathXmlApplicationContext("classpath:customLabel.xml");
        User user = (User) bf.getBean("testBean");
        System.out.println(user.getUserName() + "," + user.getEmail());
    }
}
