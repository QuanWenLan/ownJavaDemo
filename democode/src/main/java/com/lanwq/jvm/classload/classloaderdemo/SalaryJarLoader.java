package com.lanwq.jvm.classload.classloaderdemo;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.SecureClassLoader;

/**
 * @program: javaDemo->SalaryJarloader
 * @description: 实现从jar包中找到class文件来加载类。
 * @author: lanwenquan
 * @date: 2021-03-21 16:15
 */
public class SalaryJarLoader extends SecureClassLoader {
    // jar包的位置
    private String jarFile;

    public SalaryJarLoader(String jarFile) {
        this.jarFile = jarFile;
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
 /*       if(name.startsWith("com.lanwq.jvm.classload.classloaderdemo")) {
            return this.findClass(name);
        }
        return super.loadClass(name);*/
        // 先从本地加载类，本地加载不到再走双亲委派模型
        // 现在的情况是，读取两个jar包，然后各自的jar包创建各自的 SalaryCaler 类，优先从本地加载
        Class<?> c;
        synchronized (getClassLoadingLock(name)) {
            // 先从缓存中找
            c = findLoadedClass(name);
            if (c == null) {
                c = findClass(name);
                if (c == null) {
                    super.loadClass(name, resolve);
                }
            }
            return c;
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("name: " + name);
        String classPath = name.replace(".", "/").concat(".class");
        InputStream fis;
        URL url;
        ByteArrayOutputStream outputStream;
        try {
            int len;
            // 注意在jar文件中读取要使用这种方式进行读取，!后面是要读取的文件
            url = new URL("jar:file:/" + this.jarFile + "!/" + classPath);
            fis = url.openStream();
            outputStream = new ByteArrayOutputStream(fis.available());
            while ((len = fis.read()) != -1) {
                outputStream.write(len);
            }
            byte[] b = outputStream.toByteArray();
            return defineClass(name, b, 0, b.length);
        } catch (FileNotFoundException e) {
//            throw new ClassNotFoundException("自定义类文件不存在。");
            System.out.println("文件正在复制中下次读取时生效。");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
