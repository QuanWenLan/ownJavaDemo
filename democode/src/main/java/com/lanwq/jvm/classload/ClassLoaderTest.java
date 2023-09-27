package com.lanwq.jvm.classload;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Vin lan
 * @className ClassLoaderTest
 * @description TODO 类加载器与instanceof 关键字演示
 * @createTime 2020-12-22  17:02
 **/
public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream stream = getClass().getResourceAsStream(fileName);
                    if (stream == null) {
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[stream.available()];
                    stream.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = classLoader.loadClass("com.lanwq.jvm.classload.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());

        System.out.println(obj instanceof ClassLoaderTest);
    }
}
