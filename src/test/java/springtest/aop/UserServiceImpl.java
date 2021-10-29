package springtest.aop;

/**
 * @author Vin lan
 * @className UserServiceImpl
 * @description
 * @createTime 2021-08-30  14:57
 **/
public class UserServiceImpl implements  UserService {
    @Override
    public void add() {
        System.out.println("-------add()-----------");
    }

    @Override
    public void add22() {
        System.out.println("-----------add22()-----------");
    }
}
