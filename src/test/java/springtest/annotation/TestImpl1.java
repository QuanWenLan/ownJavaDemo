package springtest.annotation;

import org.springframework.stereotype.Component;

/**
 * @author Vin lan
 * @className TestImple1
 * @description
 * @createTime 2022-05-20  16:00
 **/
@Component
public class TestImpl1 implements Test {
    private String name = "test1";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println("name:" + this.name);
    }
}
