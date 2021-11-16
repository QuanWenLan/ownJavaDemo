package springtest.lookup.bean;

/**
 * @author Vin lan
 * @className GetBeanTest
 * @description
 * @createTime 2021-08-17  11:41
 **/
public abstract class GetBeanTest {
    public void showMe() {
        this.getBean().showMe();
    }

    public abstract User getBean();
}
