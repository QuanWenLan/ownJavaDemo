package com.lanwq.other;

import java.security.MessageDigest;

public class AuthenticateUtil {
	
	public static String getTomcatDigestPassword(String password) {
        MessageDigest md = null ;
        try {
            md = MessageDigest.getInstance("SHA") ;
            md.update(password.getBytes()) ;
            byte[] raw = md.digest() ;
            String hex = hexConvert(raw) ;
            return hex ;
        } catch (Exception ex) {
            return password ;
        }
	}
    
    private static String hexConvert(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length * 2) ;
        for (int i = 0; i < bytes.length; i++) {
            sb.append(convertDigit((int) (bytes[i] >> 4)));
            sb.append(convertDigit((int) (bytes[i] & 0x0f)));
        }
        return (sb.toString());
    }
    
    private static char convertDigit(int value) {
        value &= 0x0f;
        if (value >= 10)
            return ((char) (value - 10 + 'a'));
        else
            return ((char) (value + '0'));
    }

    public static void main(String[] args) {
        String admin = AuthenticateUtil.getTomcatDigestPassword("admin");
        System.out.println(admin);
    }
}
