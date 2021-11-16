package springtest.cycle.dependency;

/**
 * @author Vin lan
 * @className TestSetterA
 * @description
 * @createTime 2021-08-24  11:39
 **/
public class TestSetterC {
    private TestSetterA setterA;

    public TestSetterA getSetterA() {
        return setterA;
    }

    public void setSetterA(TestSetterA setterA) {
        this.setterA = setterA;
    }
}
