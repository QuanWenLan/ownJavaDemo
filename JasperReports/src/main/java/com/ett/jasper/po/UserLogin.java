package com.ett.jasper.po;

import java.util.Date;

/**
 * @author Vin lan
 * @className UserLogin
 * @description TODO 查询出来的数据库数据
 * @createTime 2020-09-16  09:16
 **/
public class UserLogin {
    private String userId;

    private String userName;

    private String userPackage;

    private Date actionTime; // 登录操作时间

    private String newValue;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPackage() {
        return userPackage;
    }

    public void setUserPackage(String userPackage) {
        this.userPackage = userPackage;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
}
