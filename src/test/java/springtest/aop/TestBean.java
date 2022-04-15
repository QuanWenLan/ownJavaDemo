package springtest.aop;

/**
 * @author Vin lan
 * @className TestBean
 * @description 创建用于拦截的 bean
 * @createTime 2021-08-27  09:55
 **/
public class TestBean {
    private String testStr = "testStr";
    private static TestBean obj;

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }
    public void test(Pa pa) {
        System.out.println(pa.id + "," + pa.name);
        System.out.println("testgg");
        obj.testInner(pa);
    }

    public void testInner(Pa pa) {
        obj.testInner2(pa);
    }

    public void testInner2(Pa pa) {
        System.out.println("test内部对象");
    }

    public static class Pa {
        public String id = "1";
        public String name = "xiaoming";
    }

    public static TestBean createInstance() {
        if (obj == null) {
            obj = new TestBean();
        }
        return obj;
    }
}
