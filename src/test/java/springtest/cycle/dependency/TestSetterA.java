package springtest.cycle.dependency;

/**
 * @author Vin lan
 * @className TestSetterA
 * @description
 * @createTime 2021-08-24  11:39
 **/
public class TestSetterA {
    private TestSetterB setterB;

    public TestSetterB getSetterB() {
        return setterB;
    }

    public void setSetterB(TestSetterB setterB) {
        this.setterB = setterB;
    }
}
