package temptest;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @program: javaDemo->Test
 * @description:
 * @author: lanwenquan
 * @date: 2020-08-03 18:08
 */
public class Test1 {
    private static final String OPERATION_EXIT = "EXIT";

    @Test
    public void test1() {
        /*ArrayList<StringBuffer> list = new ArrayList<>();
        StringBuffer buffer = new StringBuffer("abcd");
        list.add(buffer);
        list.add(buffer);*/
//        System.out.println(test2()); // 主函数

        boolean flag = false;
        if (flag = true) {
            System.out.println("aaa");
        }

        System.out.println("请开始您的输入，EXIT/E 退出");
        //怎么让程序一直运行
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String in = scan.next();
            if (OPERATION_EXIT.equals(in.toUpperCase())
                    || OPERATION_EXIT.substring(0, 1).equals(in.toUpperCase())) {
                System.out.println("您成功已退出！");
                break;
            }
            System.out.println("您输入的值：" + in);
        }
    }

    //    @Test
    public int test2() {
        int x = 1;
        try {
            return x;
        } finally {
            ++x;
        }
    }

    @Test
    public void test3() {
        /*HashMap<String, String> map = new HashMap<>();
        map.put("name", "lanwenquan");

        Class<?> aClass = map.getClass();
        try {
            Method capacity = aClass.getDeclaredMethod("capacity");
            capacity.setAccessible(true);
            System.out.println("capacity: " + capacity.invoke(map));
            final Method size = aClass.getDeclaredMethod("size");
            size.setAccessible(true);
            System.out.println("size:" + size.invoke(map));
            System.out.println(map.get("name"));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        HashMap map = new HashMap(5);
        Object o = new Object();
        System.out.println(o.hashCode());
        System.out.println(o.hashCode() >>> 16);
    }

    @Test
    public void test4() {
        int compare = Double.compare(14.4, 15.9);
        System.out.println(compare);
    }
}
