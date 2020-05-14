package com.lanwq.thinkinginjavademo.initializationcleanup;

/**
 * @ClassName CleanUp
 * @Description TODO 垃圾回收器的唯一原因是 为了回收程序不再使用的内存 （垃圾回收只与内存有关）
 * @Author Administrator
 * @Date 2019/6/24  21:29
 * @Version 1.0
 * <p>会释放对象占据的所有内存</p>
 */
public class CleanUp {
    public void fo() throws Throwable {
        this.finalize();
    }
}
