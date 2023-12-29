package springtest.annotation.multipleResource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lan
 * @createTime 2023-12-11  10:45
 **/
@Configuration
public class SpringConfiguration {

    @Bean(name = "service1")
    public Service1 service1() {
        return new Service1();
    }

    @Bean(name = "service2")
    public Service2 service2() {
        return new Service2();
    }
}
