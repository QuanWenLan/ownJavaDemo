package com.ett.jasper.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Vin lan
 * @className UserLoginList
 * @description TODO 数据展示
 * @createTime 2020-09-16  09:15
 **/
public class UserLoginInfo {
    private String userId;

    private String userName;

    private String userPackage;

    private List<LoginInfo> LoginInfoList = new ArrayList<LoginInfo>();

    private static class LoginInfo {
        private Date lastLoginTime;

        private String IPAddress;

        private String stationId;

        public Date getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(Date lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getIPAddress() {
            return IPAddress;
        }

        public void setIPAddress(String IPAddress) {
            this.IPAddress = IPAddress;
        }

        public String getStationId() {
            return stationId;
        }

        public void setStationId(String stationId) {
            this.stationId = stationId;
        }

        @Override
        public String toString() {
            return "LoginInfo{" +
                    "lastLoginTime=" + lastLoginTime +
                    ", IPAddress='" + IPAddress + '\'' +
                    ", stationId='" + stationId + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserLoginInfo{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPackage='" + userPackage + '\'' +
                ", LoginInfoList=" + LoginInfoList +
                '}';
    }
}
