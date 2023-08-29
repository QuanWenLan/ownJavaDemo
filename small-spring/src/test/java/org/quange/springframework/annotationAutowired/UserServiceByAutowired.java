package org.quange.springframework.annotationAutowired;

import org.quange.springframework.bean.UserDao;
import org.quange.springframework.beans.factory.annotation.Autowired;
import org.quange.springframework.beans.factory.annotation.Value;
import org.quange.springframework.context.stereotype.Component;

import java.util.Random;

/**
 * @author Lan
 * @createTime 2023-08-29  11:49
 **/
@Component(value = "userService")
public class UserServiceByAutowired implements IUserServiceAutowired {
    @Value("${token}")
    private String token;

    @Autowired
    private UserDaoByAutowired userDao;

    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDao.queryUserName("10001") + "，" + token;
    }

    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }

    @Override
    public String toString() {
        return "UserService#token = { " + token + " }";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
