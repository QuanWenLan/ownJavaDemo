package springtest.annotation;

import org.springframework.stereotype.Component;

/**
 * @author Vin lan
 * @className AnnotationTest
 * @description 注解开发测试
 * @createTime 2022-04-12  14:48
 **/
@Component(value = "annotationTest")
public class AnnotationTest {
    public void print() {
        System.out.println("name=a");
    }
}
