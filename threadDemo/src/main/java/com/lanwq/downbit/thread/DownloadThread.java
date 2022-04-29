package com.lanwq.downbit.thread;

import com.lanwq.downbit.HttpDownLoadMain;
import com.lanwq.downbit.util.FileUtil;
import com.lanwq.downbit.util.HttpUtil;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.util.concurrent.Callable;

/**
 * @program: javaDemo->DownloadThread
 * @description: 下载线程
 * @author: lanwenquan
 * @date: 2020-08-03 16:31
 */
public class DownloadThread implements Callable<Boolean> {
    // 每次读取的数据块大小
    private static int BYTE_SIZE = 1024 * 100;
    // 下载链接
    private String url;
    // 下载开始位置
    private long startPos;
    // 要下载的文件区块大小
    private Long endPos;
    // 标识多线程下载切分的第几部分
    private Integer part;

    public DownloadThread(String url, long startPos, long endPos, Integer part) {
        this.url = url;
        this.startPos = startPos;
        this.endPos = endPos;
        this.part = part;
    }

    @Override
    public Boolean call() throws Exception {
        if (url == null || url.trim().equals("")) {
            throw new RuntimeException("> 下载路径不正确");
        }
        // 文件名
        String httpFileName = HttpUtil.getHttpFileName(url);
        if (part != null) {
            httpFileName = httpFileName + HttpDownLoadMain.FILE_TEMP_SUFFIX + part;
        }
        httpFileName = "D:\\download\\" + httpFileName;

        // 获取本地的临时文件的大小
        long localFileContentLength = FileUtil.getLocalFileSize(httpFileName);
        // 判断已经存在了这个文件，是之前下载的，然后突然停止后又开始下载了，文件即已经存在了
        if (localFileContentLength >= endPos - startPos) {
            System.out.println("> " + httpFileName + " 已经下载完毕，无需重新下载。");
            LogThread.DOWNLOAD_FINISH.addAndGet(1);
            return true;
        }

        // 新的文件要重新下载
        System.out.println("开始获取：" + startPos + " 字节 到 " + endPos + " 字节内容");
        HttpURLConnection httpUrlConnection = HttpUtil.getHttpUrlConnection(url, startPos, endPos);
        // 未完成下载的大小
        int unfinishedSize = httpUrlConnection.getContentLength();
        System.out.println("获取到的文件大小：" + unfinishedSize);
        // 获取输入流
        try (InputStream inputStream = httpUrlConnection.getInputStream();
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
             RandomAccessFile oSavedFile = new RandomAccessFile(httpFileName, "rw")) {
            // 文件写入开始位置 localFileContentLength
            oSavedFile.seek(localFileContentLength);
            LogThread.DOWNLOAD_SIZE.addAndGet(localFileContentLength);
            byte[] bytes = new byte[BYTE_SIZE];
            int len = 0;
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                // 写文件到oSavedFile
                oSavedFile.write(bytes, 0, len);
                LogThread.DOWNLOAD_SIZE.addAndGet(len);
            }
            oSavedFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("\n> ERROR! 要下载的文件路径不存在 " + url);
            return false;
        } catch (Exception e) {
            System.out.println("\n> 下载出现异常");
            e.printStackTrace();
            return false;
        } finally {
            httpUrlConnection.disconnect();
            LogThread.DOWNLOAD_FINISH.addAndGet(1);
        }
        return true;
    }

    @Override
    public String toString() {
        return "DownloadThread{" +
                "startPos=" + startPos +
                ", endPos=" + endPos +
                ", part=" + part +
                '}';
    }
}
