package com.lanwq.jvm.classload.classloaderdemo;

import java.io.*;
import java.security.SecureClassLoader;

/**
 * @program: javaDemo->SalaryClassLoader
 * @description: 从  .myclass文件中加载对象
 * @author: lanwenquan
 * @date: 2021-03-07 13:53
 */
public class SalaryClassLoader extends SecureClassLoader {

    private String classPath;

    public SalaryClassLoader(String classPath) {
        this.classPath = classPath;
    }

    /**
     * 需要重现从 .myclass 文件中加载
     **/
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("进来了这个方法" + this.classPath + " name: " + name);
        String filePath = this.classPath + name.replace(".", "\\").concat(".myClass");
        FileInputStream fis;
        try {
            int len;
            fis = new FileInputStream(new File(filePath));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(fis.available());
            while ((len = fis.read()) != -1) {
                outputStream.write(len);
            }
            byte[] b = outputStream.toByteArray();
            return defineClass(name, b, 0, b.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }
}
