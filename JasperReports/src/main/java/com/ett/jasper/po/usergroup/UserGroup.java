package com.ett.jasper.po.usergroup;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Vin lan
 * @className UserGroup
 * @description TODO
 * @createTime 2020-09-10  15:19
 **/
public class UserGroup {
    private String roleName;

    private String roleDesc;

    private String userId;

    private String userName;

    private Date lastUpdateTime; // 查询一定要指定成这个，指定成Object也不行

    public UserGroup() {
    }

    public UserGroup(String roleName, String roleDesc, String userId, String userName, Date lastUpdateTime) {
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.userId = userId;
        this.userName = userName;
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "UserGroupListStruct{" +
                "roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
