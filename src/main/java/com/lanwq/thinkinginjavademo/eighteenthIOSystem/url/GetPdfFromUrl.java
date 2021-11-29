package com.lanwq.thinkinginjavademo.eighteenthIOSystem.url;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

/**
 * @author Vin lan
 * @className GetPdfFromUrl
 * @description TODO
 * @createTime 2021-01-08  12:24
 **/
public class GetPdfFromUrl {
    public static void main(String[] args) throws Exception {
        String href  = "https://htm.sf-express.com/hk/tc/download/7-11_HK_TC.pdf";
        URL pdfUrl = new URL(href);
        HttpsURLConnection connection = (HttpsURLConnection) pdfUrl.openConnection();
        InputStream tempIn = connection.getInputStream();
        String fileName = "E:\\" + href.substring(href.lastIndexOf("/") + 1);
        File f = new File(fileName);
        OutputStream tempOut = new FileOutputStream(f);
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = tempIn.read(buffer)) != -1) {
            tempOut.write(buffer, 0, len);
        }
        tempOut.write(buffer);
        tempOut.close();
        tempIn.close();
    }
}
