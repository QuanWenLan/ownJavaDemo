package com.lanwq.downbit.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @program: javaDemo->HttpUtiil
 * @description: Http工具类，获取http连接和资源
 * @author: lanwenquan
 * @date: 2020-08-03 16:28
 */
public class HttpUtil {

    /**
     *
     * @param url 下载路径
     * @return 文件名称
     */
    public static String getHttpFileName(String url) {
        int index = url.lastIndexOf("/");
        return url.substring(index + 1);
    }

    /**
     * 获取Http连接
     * @param url 网络地址
     * @return
     */
    public static HttpURLConnection getHttpUrlConnection(String url) {
        HttpURLConnection HttpUrlConnection = null;
        try {
            URL httpUrl = new URL(url);
            // 获取连接
            HttpUrlConnection = (HttpURLConnection)httpUrl.openConnection();
            HttpUrlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.105 Safari/537.36 Edg/84.0.522.49");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return HttpUrlConnection;
    }

    /**
     * 获取Http连接
     * @param url
     * @param start
     * @param end
     * @return
     * @throws IOException
     */
    public static HttpURLConnection getHttpUrlConnection(String url, long start, Long end) throws IOException {
        HttpURLConnection httpUrlConnection = getHttpUrlConnection(url);
        if (end != null) {
            httpUrlConnection.setRequestProperty("RANGE", "bytes=" + start + "-" + end + "");
        } else {
            httpUrlConnection.setRequestProperty("RANGE", "bytes=" + start + "-");
        }
        return httpUrlConnection;
    }

    /**
     * 获取网络文件大小
     * @param url
     * @return
     */
    public static long getHttpFileContentLength(String url) {
        HttpURLConnection httpURLConnection = getHttpUrlConnection(url);
        int contentLength = httpURLConnection.getContentLength();
        httpURLConnection.disconnect();
        return contentLength;
    }

    /**
     * 获取网络Etag
     * @param url
     * @return
     * @throws IOException
     */
    public static String getHttpFileEtag(String url) throws IOException {
        HttpURLConnection httpUrlConnection = getHttpUrlConnection(url);
        Map<String, List<String>> headerFields = httpUrlConnection.getHeaderFields();
        List<String> eTagList = headerFields.get("ETag");
        return eTagList.get(0);
    }
}
