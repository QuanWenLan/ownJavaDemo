package springtest.cycle.dependency;

/**
 * @author Vin lan
 * @className TestC
 * @description
 * @createTime 2021-08-19  17:30
 **/
public class TestC {
    private TestA testA;

    // 构造器注入
    public TestC(TestA testA) {
        this.testA = testA;
    }

    public void c() {
        testA.a();
    }

    public TestA getTestA() {
        return testA;
    }

    public void setTestA(TestA testA) {
        this.testA = testA;
    }
}
