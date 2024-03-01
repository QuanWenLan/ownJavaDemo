package com.lanwq.thread.downbit;

import com.lanwq.thread.downbit.thread.DownloadThread;
import com.lanwq.thread.downbit.thread.LogThread;
import com.lanwq.thread.downbit.util.FileUtil;
import com.lanwq.thread.downbit.util.HttpUtil;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @program: javaDemo->HttpDownLoadMain
 * @description: 多线程断点续传下载
 * @author: lanwenquan
 * @date: 2020-08-03 16:27
 */
public class HttpDownLoadMain {
    // 定义下载线程数量
    public static final Integer DOWNLOAD_THREAD_NUM = 5;

    // 定义要使用的线程池
    private ExecutorService executor = Executors.newFixedThreadPool(DOWNLOAD_THREAD_NUM + 1);

    // 临时文件后缀
    public static String FILE_TEMP_SUFFIX = ".temp";

    public static void main(String[] args) {
        // 下载链接
//        String url = "http://wppkg.baidupcs.com/issue/netdisk/yunguanjia/BaiduYunGuanjia_7.0.1.1.exe";
        String url = "https://issuepcdn.baidupcs.com/issue/netdisk/yunguanjia/BaiduNetdisk_7.15.0.15.exe";
        HttpDownLoadMain fileDownload = new HttpDownLoadMain();
        fileDownload.download(url);
    }

    /**
     *     文件下载主方法
      */
    private void download(String url) {
        //1：获取文件名
        String fileName = HttpUtil.getHttpFileName(url);
        fileName = "D:\\download\\" + fileName;
        //2：获取下载过来的文件大小
        long localFileSize = FileUtil.getLocalFileSize(fileName);
        //3:获取网络文件的大小
        long httpFileContentLength = HttpUtil.getHttpFileContentLength(url);
        if (localFileSize >= httpFileContentLength) {
            System.out.println("> " + fileName + " 已经下载完成，不需要进行下载");
            return;
        }
        // 这里为什么要用这个list放 Future<Boolean>？？？
        List<Future<Boolean>> futureList = new ArrayList<>();
        if (localFileSize > 0) {
            System.out.println("> 开始断点续传 " + fileName);
        } else {
            System.out.println("> 开始下载文件 " + fileName);
        }
        System.out.println("> 开始下载时间 " + LocalDateTime.now());
        long startTime = System.currentTimeMillis();
        // 前几个线程所下载的字节数量
        long size = httpFileContentLength / DOWNLOAD_THREAD_NUM;
        // 最后一个线程所下载的文件字节数
        long lastSize = httpFileContentLength - (httpFileContentLength / DOWNLOAD_THREAD_NUM * (httpFileContentLength - 1));
        for (int i = 0; i < DOWNLOAD_THREAD_NUM + 1; i++) {
            long start = size * i;
            long downloadWindow = (i == DOWNLOAD_THREAD_NUM) ? lastSize : size;
            long end = start + downloadWindow;
            if (start != 0) {
                start++;
            }
            DownloadThread downloadThread = new DownloadThread(url, start, end, i);
            // 提交之时就已经是开始了任务的执行
            Future<Boolean> future = executor.submit(downloadThread);
            futureList.add(future);
        }
        // 日志记录
        LogThread logThread = new LogThread(httpFileContentLength);
        Future<Boolean> future = executor.submit(logThread);
//        futureList.add(future);

        // 获取返回的结果，会一直获取线程执行完之后的结果。要取到结果否则就会阻塞
        for (Future<Boolean> booleanFuture : futureList) {
            try {
                booleanFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("> 文件下载完毕 " + fileName + "，本次下载耗时：" + (System.currentTimeMillis() - startTime) / 1000 + "s");
        System.out.println("> 结束下载时间 " + LocalDateTime.now());
        // 文件合并
        boolean merge = merge(fileName);
        if (merge) {
            System.out.println();
            // 清理分段文件
            clearTemp(fileName);
        }
        System.out.println("> 本次文件下载结束");
        System.exit(0);
    }

    /**
     *     文件合并
      */
    private boolean merge(String fileName) {
        System.out.println("> 开始合并文件 " + fileName);
        byte[] buffer = new byte[1024 * 10];
        try (RandomAccessFile oSavedFile = new RandomAccessFile(fileName, "rw")) {
            // 要合并 DOWNLOAD_THREAD_NUM 次文件
            for (int i = 0; i < DOWNLOAD_THREAD_NUM; i++) {
                String name = fileName + HttpDownLoadMain.FILE_TEMP_SUFFIX + i;
                if (FileUtil.getLocalFileSize(name) > 0) {
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(name));
                    int len = 0;
                    while ((len = bis.read(buffer)) != -1) {
                        oSavedFile.write(buffer, 0, len);
                    }
                    // 这里合并完了文件需要关闭流，不然删除不掉整个文件
                    bis.close();
                }
            }
//            oSavedFile.close();
            System.out.println("> 文件合并完毕");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 清理临时文件
     */
    private void clearTemp(String fileName) {
        System.out.println("> 开始清理临时文件 " + fileName + FILE_TEMP_SUFFIX + "0-" + (DOWNLOAD_THREAD_NUM - 1));
        for (int i = 0; i < DOWNLOAD_THREAD_NUM; i++) {
            System.out.println("> 清理临时文件 " + fileName + FILE_TEMP_SUFFIX + i);
            File file = new File(fileName + FILE_TEMP_SUFFIX + i);
            final boolean delete = file.delete();
        }
        System.out.println("> 临时文件清理完毕 " + fileName + FILE_TEMP_SUFFIX + "0-" + (DOWNLOAD_THREAD_NUM - 1));
    }
}
