package springtest.aop;

/**
 * @author Vin lan
 * @className TestBean
 * @description 创建用于拦截的 bean
 * @createTime 2021-08-27  09:55
 **/
public class TestBean {
    private String testStr = "testStr";

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }
    public void test() {
        System.out.println("testgg");
    }
}
