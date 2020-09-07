package com.lanwq.trycatch;

/**
 * @author Vin lan
 * @className TryWithResource
 * @description TODO Java8 try catch 不需要使用finally来进行一些通道，接口的关闭
 * @createTime 2020-09-03  11:09
 * 实现这个功能必须要实现AutoClosable接口，该接口要重写一个close方法
 * https://juejin.im/entry/6844903446185951240
 **/
public class TryWithResource2 {
    /**
     * 修改例子为手动try catch异常
     */
    public static void main(String[] args){
        try {
            /*test1();
            System.out.println("====test1()优化===");
            test1Optimize();*/

            test2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 运行test1之后.如果没有catch，只报一个异常， java.lang.Exception: close 异常 ,而sendData抛出的Exception被忽略了
     */
    private static void test1() throws Exception {
        Connection2 conn = null;
        try {
            conn = new Connection2();
            conn.sendData();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    private static void test1Optimize() throws Exception {
        Connection2 conn = null;
        try {
            conn = new Connection2();
            conn.sendData();
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    private static void test2() throws Exception {
        try (Connection2 conn = new Connection2()) {
            conn.sendData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
