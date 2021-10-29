package springtest.lookup.bean;

/**
 * @author Vin lan
 * @className Student
 * @description
 * @createTime 2021-08-17  14:39
 **/
public class Student extends User {
    @Override
    public void showMe() {
        System.out.println("i am student");
    }
}
