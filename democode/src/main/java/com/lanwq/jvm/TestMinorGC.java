package com.lanwq.jvm;

/**
 * @author Vin lan
 * @className TestMinorGC
 * @description TODO
 * @createTime 2020-11-09  12:23
 **/
public class TestMinorGC {
    /**
     * VM 参数 gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     *
     * @param args
     */
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[1024 * 1024 * 2];
        allocation2 = new byte[1024 * 1024 * 2];
        allocation3 = new byte[1024 * 1024 * 2];
        allocation4 = new byte[1024 * 1024 * 4]; // 出现Minor GC
    }
}
