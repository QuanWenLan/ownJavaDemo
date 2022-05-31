package springtest.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Vin lan
 * @className AutowiredTest
 * @description
 * @createTime 2022-05-20  15:59
 **/
@Component(value = "autowiredTest")
public class AutowiredTest {
    @Autowired
    @Qualifier(value = "testImpl1")
    private Test test;

    @Resource
    private Test testImpl2;

    public void testPrint() {
        test.printName();
    }

    public void testPrint2() {
        testImpl2.printName();
    }
}
