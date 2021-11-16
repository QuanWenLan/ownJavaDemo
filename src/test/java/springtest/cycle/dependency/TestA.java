package springtest.cycle.dependency;

/**
 * @author Vin lan
 * @className TestA
 * @description
 * @createTime 2021-08-19  17:30
 **/
public class TestA {
    private TestB testB;

    // 构造器注入
    public TestA(TestB testB) {
        this.testB = testB;
    }

    public void a() {
        testB.b();
    }

    public TestB getTestB() {
        return testB;
    }

    public void setTestB(TestB testB) {
        this.testB = testB;
    }
}
