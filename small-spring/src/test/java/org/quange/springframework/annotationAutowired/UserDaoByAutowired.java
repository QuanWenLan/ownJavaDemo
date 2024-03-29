package org.quange.springframework.annotationAutowired;

import org.quange.springframework.context.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lan
 * @createTime 2023-08-21  11:16
 **/
@Component("userDao")
public class UserDaoByAutowired {
    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "小傅哥");
        hashMap.put("10002", "八杯水");
        hashMap.put("10003", "阿毛");
    }

    public void initDataMethod() {
        System.out.println("执行：init-method，新添加一个数据");
        hashMap.put("10004", "小明");
    }

    public void destroyDataMethod(){
        System.out.println("执行：destroy-method");
        hashMap.clear();
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
