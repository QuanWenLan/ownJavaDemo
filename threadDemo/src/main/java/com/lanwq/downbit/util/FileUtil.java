package com.lanwq.downbit.util;

import java.io.File;

/**
 * @program: javaDemo->FileUtils
 * @description: 文件工具类
 * @author: lanwenquan
 * @date: 2020-08-03 16:29
 */
public class FileUtil {

    public static long getLocalFileSize(String fileName) {
        File localFile = new File(fileName);
        return localFile.exists() && localFile.isFile() ? localFile.length() : 0L;
    }
}
