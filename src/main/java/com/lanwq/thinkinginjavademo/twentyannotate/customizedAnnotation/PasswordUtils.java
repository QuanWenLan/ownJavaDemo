package com.lanwq.thinkinginjavademo.twentyannotate.customizedAnnotation;

import java.util.List;

/**
 * @author Vin lan
 * @className PasswordUtils
 * @description TODO ths use of the {@link UseCase}
 * @createTime 2020-11-04  15:18
 **/
public class PasswordUtils {
    @UseCase(id = 47, description = "Password must contains at least one numeric")
    public boolean validatePassword(String password) {
        return (password.matches("\\w*\\d\\w*"));
    }

    @UseCase(id = 48)
    public String encryptPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }

    @UseCase(id = 49, description = "New password can't equal previously used ones")
    public boolean checkForNewPassword(List<String> prePasswords, String password) {
        return !prePasswords.contains(password);
    }

    public static void main(String[] args) {
        System.out.println("\\");
        PasswordUtils passwordUtils = new PasswordUtils();
        System.out.println(passwordUtils.validatePassword("abcdABCD"));
    }
}
