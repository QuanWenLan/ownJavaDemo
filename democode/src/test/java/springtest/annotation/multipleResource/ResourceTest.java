package springtest.annotation.multipleResource;

import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author Lan
 * @createTime 2023-12-11  10:53
 **/
@Configuration
public class ResourceTest {

    @Resource(name = "service1")
    private Service1 service1;

    @Resource
    private Service2 service;
}
