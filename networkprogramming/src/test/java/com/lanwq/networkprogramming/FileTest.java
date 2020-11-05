package com.lanwq.networkprogramming;

import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Vin lan
 * @className FileTest
 * @description TODO
 * @createTime 2020-10-26  14:12
 **/
public class FileTest {
    @Test
    public void getFileFromUrl() {
//        String url = "https://ucc-private-download.oss-cn-beijing.aliyuncs.com/19e0ec64e52140dbb775ed8b58c2ff27.pdf?Expires=1603695146&OSSAccessKeyId=LTAIvsP3ECkg4Nm9&Signature=N4SuGo1ZbgskM1cG%2B2wywNynJlk%3D";
//        String developerManual = "https://ucc-private-download.oss-cn-beijing.aliyuncs.com/66995068b45c4ebfa74afcfc2e76212c.pdf?Expires=1603695339&OSSAccessKeyId=LTAIvsP3ECkg4Nm9&Signature=JOQofzu1Sp8cYwr%2BBaZMe%2FZwDqg%3D";
        String developerManual = "http://file.allitebooks.com/20161202/Play%20for%20Java.pdf";
        try {
            URL url1 = new URL(developerManual);
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            Files.copy(conn.getInputStream(), Paths.get("E:\\pdf文档\\playForJava.pdf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
