package com.google.android.fcm.server;

import com.google.firebase.messaging.MessagingErrorCode;

/**
 * @author Vin lan
 * @className MessageResponse
 * @description
 * @createTime 2022-04-25  14:42
 **/
public class MessageResponse {
    private String deviceToken;
    private String returnMsg;
    private MessagingErrorCode errorCode;
    private ReturnCode returnCode = ReturnCode.SUCCESS;
    private String topic;

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public MessagingErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(MessagingErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ReturnCode getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(ReturnCode returnCode) {
        this.returnCode = returnCode;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "deviceToken='" + deviceToken + '\'' +
                ", returnMsg='" + returnMsg + '\'' +
                ", errorCode=" + errorCode +
                ", returnCode=" + returnCode +
                ", topic='" + topic + '\'' +
                '}';
    }
}
