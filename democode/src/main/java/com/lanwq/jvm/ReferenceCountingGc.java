package com.lanwq.jvm;

/**
 * @author Vin lan
 * @className ReferenceCountingGc
 * @description TODO
 * @createTime 2020-10-26  16:45
 **/
public class ReferenceCountingGc {
    public Object instance = null;
    private static final int MB = 1024 * 1024;
    private byte[] bigSize = new byte[2 * MB];

    public static void main(String[] args) {
        ReferenceCountingGc objA = new ReferenceCountingGc();
        ReferenceCountingGc objB = new ReferenceCountingGc();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
        // 添加虚拟机参数 -XX:+PrintGCDetails
        // 假设在这发生GC，objA,objB能否被回收
        System.gc();
    }
}
