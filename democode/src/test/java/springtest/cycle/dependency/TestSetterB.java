package springtest.cycle.dependency;

/**
 * @author Vin lan
 * @className TestSetterA
 * @description
 * @createTime 2021-08-24  11:39
 **/
public class TestSetterB {
//    private TestSetterC setterC;

    private TestSetterA setterA;

//    public TestSetterC getSetterC() {
//        return setterC;
//    }
//
//    public void setSetterC(TestSetterC setterC) {
//        this.setterC = setterC;
//    }

    public TestSetterA getSetterA() {
        return setterA;
    }

    public void setSetterA(TestSetterA setterA) {
        this.setterA = setterA;
    }
}
