package springtest.cycle.dependency;

/**
 * @author Vin lan
 * @className TestB
 * @description
 * @createTime 2021-08-19  17:30
 **/
public class TestB {
    private TestC testC;

    // 构造器注入
    public TestB(TestC testC) {
        this.testC = testC;
    }

    public void b() {
        testC.c();
    }

    public TestC getTestC() {
        return testC;
    }

    public void setTestC(TestC testC) {
        this.testC = testC;
    }
}
