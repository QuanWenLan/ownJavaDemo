package com.lanwq.thinking_in_java_demo.IOSystem.clone;

import java.io.*;

/**
 * @program: javaDemo->CloneUtil
 * @description: 对象的深度克隆工具类，使用对象的序列化和反序列化实现
 * @author: lanwenquan
 * @date: 2020-04-23 23:02
 */
public class CloneUtil {
    private CloneUtil() {
        throw new AssertionError();
    }

    public static <T extends Serializable> T clone(T obj) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(obj);

        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);
        return (T)ois.readObject();
    }
}
