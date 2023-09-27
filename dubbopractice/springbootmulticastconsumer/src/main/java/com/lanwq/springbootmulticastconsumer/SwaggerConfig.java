package com.lanwq.springbootmulticastconsumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .tags(new Tag("DemoController", "演示服务"))
                .select()
                // 当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.lanwq.springbootmulticastconsumer.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //构建 api 文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("dubbo远程调用服务的操作(广播模式)")
                //创建人
                .contact(new Contact("程序员欣宸", "https://github.com/zq2599/blog_demos", "zq2599@gmail.com"))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }
}