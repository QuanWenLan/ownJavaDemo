package springtest.cycle.dependency;

/**
 * @author Vin lan
 * @className TestSetterA
 * @description
 * @createTime 2021-08-24  11:39
 **/
public class TestSetterB {
    private TestSetterC setterC;

    public TestSetterC getSetterC() {
        return setterC;
    }

    public void setSetterC(TestSetterC setterC) {
        this.setterC = setterC;
    }
}
