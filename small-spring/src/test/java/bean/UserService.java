package bean;

/**
 * @author Lan
 * @createTime 2023-08-18  17:05
 **/
public class UserService {
    private String userId;
    private UserDao userDao;

    public UserService(String userId) {
        this.userId = userId;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + userDao.queryUserName(userId));
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "userId='" + userId + '\'' +
                ", userDao=" + userDao +
                '}';
    }
}
