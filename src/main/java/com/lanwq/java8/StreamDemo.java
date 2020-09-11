package com.lanwq.java8;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @program: javaDemo->StreamDemo
 * @description: 流的一些操作  https://www.javazhiyin.com/63001.html
 * @author: lanwenquan
 * @date: 2020-08-07 15:19
 */
public class StreamDemo {
    public static void main(String[] args) {
        test1();
    }

    // 1.流的创建
    private static void test1() {
        // 第一种 集合
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> stream = list.stream();
        // 第二种 数值
        final Stream<String> stream1 = Stream.of("a", "b", "c");
        // 第三种 数组
        String[] ss = new String[]{"a", "b", "c"};
        final Stream<String> stream2 = Arrays.stream(ss);
        // 第四种 文件
        try(Stream<String> stream3 = Files.lines(Paths.get("1.txt"))) {
            stream3.forEach(System.out::print);
        } catch (Exception e) {

        }
    }
}
