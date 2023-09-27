package springtest.dependency;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Vin lan
 * @className MainConfig
 * @description TODO
 * @createTime 2021-02-01  09:20
 **/
@Configuration
//@Import({TestService1.class, TestService2.class})
@ComponentScan({"springtest.dependency"})
public class MainConfig {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);

//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
//        context.start();

        TestService1 testService1 = (TestService1) context.getBean("testService1");
        TestService2 testService2 = (TestService2) context.getBean("testService2");
        System.out.println(testService1);
        System.out.println(testService2);
    }
    /** https://juejin.cn/post/6859189194837721102 参考,这个也可以参考：https://mp.weixin.qq.com/s/VpCt49_Li35caK5IaQTuNg
     * 1. 属性注入不会报错，会先调用 空的构造方法
     * 2. setter注入不会报错，会先调用 空的构造方法
     * 3. 采用构造器注入，报错，出现循环依赖
     * 4. TestService1 构造注入TestService2 ，TestService2  setter注入TestService1： 报错，出现循环依赖
     *
     * 5. TestService2 构造注入TestService1，TestService1  setter注入TestService2： 正常，解决了循环依赖
     */
}
/**
 * 关于Spring bean的创建，其本质上还是一个对象的创建，既然是对象，读者朋友一定要明白一点就是，一个完整的对象包含两部分：
 * 当前对象实例化和对象属性的实例化。在Spring中，对象的实例化是通过反射实现的，而对象的属性则是在对象实例化之后通过一定的方式设置的。
 *
 * 作者：SoWhat1412
 * 链接：https://juejin.cn/post/6859189194837721102
 * 来源：掘金
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
