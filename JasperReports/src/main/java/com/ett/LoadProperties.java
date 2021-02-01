package com.ett;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Vin lan
 * @className LoadProperties
 * @description TODO
 * @createTime 2021-01-11  10:51
 **/
public class LoadProperties {
    private List<String> areaHkList;
    private final static String PROPERTIES_PATH = "localPickupInfo.properties";
    public static Properties properties = new Properties();

    static {
        try {
            properties.load(new InputStreamReader(Objects.requireNonNull(LoadProperties.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH)), StandardCharsets.UTF_8));
            String property = properties.getProperty("area.HK");
            System.out.println(property);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("load");
        String address = "天空之城 取件/本地寄件";
        System.out.println(address);
        System.out.println(address.replaceAll("(取|寄|本地寄|/)件*", ""));
    }
}
