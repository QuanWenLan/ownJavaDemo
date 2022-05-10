package com.google.android.fcm.server;

/**
 * @author Vin lan
 * @className ReturnCode
 * @description TODO
 * @createTime 2022-04-25  15:05
 **/
public enum ReturnCode {
    SUCCESS("0"),
    FAIL("1"),
    DEADLINE_EXCEEDED("2");


    private String value;

    ReturnCode(String v) {
        this.value = v;
    }

    public String getValue() {
        return this.value;
    }
}
