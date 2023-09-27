package com.lanwq.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vin lan
 * @className TestOutOfMemoryError
 * @description TODO Java堆内存溢出异常测试
 * @createTime 2020-10-16  16:30
 * @see <p>《参考深入理解Java虚拟机：JVM高级特性与最佳实践（第二版）》---- 2.4.1章节 </p>
 **/
public class TestOutOfMemoryError {
    /**
     * Java堆用于存储对象实例，只要不断地创建对象，并且保证GC Roots到对象之间有可达路径来避免垃圾回收机制清除这些对象，
     * 那么在对象数量到达最大堆的容量限制后就会产生内存溢出异常只要不停的创建对象
     * ======================
     * 设置虚拟机参数：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     * -XX:+HeapDumpOnOutOfMemoryError 可以让虚拟机在出现内存溢出异常时Dump出当前的内存堆转储快照以便事后进行分析
     */

    static class OOMObject{

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
