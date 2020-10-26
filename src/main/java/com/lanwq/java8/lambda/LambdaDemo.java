package com.lanwq.java8.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Vin lan
 * @className LambdaDemo
 * @description TODO 使用lambda的一些案例
 * @createTime 2020-10-10  16:19
 **/
public class LambdaDemo {
    public static void main(String[] args) {
        /*replaceAnonymousInnerClass();
        System.out.println("=============");
        traversalCollection();
        System.out.println("=============");
        filterWithLambda();
        System.out.println("=============");
        filterWithMultiplyPredicate();
        System.out.println("=============");*/
        limitWithLambda();
        System.out.println("=============");
        lambdaWithSorted();
        System.out.println("=============");
        maxAndMin();
        System.out.println("=============");
        mapWithLambda();
        System.out.println("=============");
        reduceWithLambda();
        System.out.println("=============");
        collectionWithLambda();
    }

    /**
     * 1:替代匿名内
     */
    public static void replaceAnonymousInnerClass() {
        /**
         * new Thread(new Runnable() {
         *   @Override
         *   public void run() {
         *     System.out.println("do it...");
         *   }
         * }).start();
         */
        new Thread(() -> {
            System.out.println("线程开始。。。");
        }).start();
    }

    /**
     * 2:遍历集合
     */
    public static void traversalCollection() {
        List<String> list = Arrays.asList("Hello", "World", "Java");
        for (String str : list) {
            System.out.println(str);
        }

        list.forEach((String str) -> {
            System.out.println(str);
        });
        // 或者
        list.forEach(System.out::println);
    }

    /**
     * 3:filter 过滤
     * filter() 方法是 Stream 提供的对数据进行过滤的 API，需要结合 Lambda 表达式来处理，
     */
    public static void filterWithLambda() {
        List<String> strings = Arrays.asList("Hello", "World", "Java");
        strings.stream().filter((String str) -> {
            return str.length() >= 5;
        }).forEach(System.out::println);
    }

    /**
     * 4:Predicate 多条件过滤
     * 如果需要通过多个条件对集合进行过滤，可以使用 Predicate 来处理，Predicate 可以定义具体的过滤条件，
     * 调用多次 filter() 方法，通过传入不同的 Predicate 对象来进行过滤，具体操作如下所示
     */
    public static void filterWithMultiplyPredicate() {
        List<String> list = Arrays.asList("Hello", "World", "Java");
//        Predicate<String> p = (str) -> {return str.length() >= 5;};
        Predicate<String> predicate1 = str -> str.length() >= 5;
        Predicate<String> predicate2 = str -> str.startsWith("H");
        list.stream()
                .filter(predicate1)
                .filter(predicate2)
                .forEach(System.out::println);
        // 也可以使用 Predicate 对象的 and() 方法，对多个 Predicate 对象进行且运算，或者用 or() 进行或运算
        list.stream()
                .filter(predicate1.and(predicate2))
                .forEach(System.out::println);
    }

    /**
     * 5:limit 使用limit来进行数据的截取
     * limit也是stream的一个方法，和数据库里面的limit一样，原理与 SQL 语句的 limit 一致，从 0 开始
     */
    public static void limitWithLambda() {
        List<String> list = Arrays.asList("Hello", "World", "Java");
        list.stream().limit(2).forEach(str -> {
            System.out.print(str + " ");
        });
        System.out.println("  ==  ");
        // 可以和filter 结合起来一起使用
        list.stream().filter(str -> str.length() >= 5).filter((String str) -> {
            return str.startsWith("H");
        }).limit(2).forEach(str -> {
            System.out.print(str + " ");
        });
    }

    /**
     * 6:排序 sorted(),默认是升序排列
     */
    public static void lambdaWithSorted() {
        List<Integer> list = Arrays.asList(1, 6, 2, 3, 5, 4);
        list.stream()
                .sorted()
                .forEach(num -> System.out.println(num));
        System.out.println("------------------");
        // 也可以使用指定的排序器  Comparator.reverseOrder()
        list.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(num -> System.out.println(num));
    }

    /**
     * 7:max()返回最大值，min()返回最小值
     */
    public static void maxAndMin() {
        List<Integer> list = Arrays.asList(1, 6, 2, 3, 5, 4);
        System.out.println(list.stream().max(Integer::compareTo).get());
        System.out.println(list.stream().min(Integer::compareTo).get());
    }

    /**
     * 8: map() 对集合中元素进行特定操作
     */
    public static void mapWithLambda() {
        List<Integer> list = Arrays.asList(1, 6, 2, 3, 5, 4);
        list.stream()
                .map(num -> num + 10)
                .forEach(num -> System.out.println(num));
    }

    /**
     * 9、reduce 对集合中元素进行特定操作
     * reduce() 和 map() 一样，都可以对集合中元素进行操作，区别在于 reduce() 是将所有元素按照传入的逻辑进行处理，
     * 并将结果合并成一个值返回，如返回集合所有元素之和，操作如下所示。
     * 需要注意的是 reduce() 的返回值是 Optional 类型，需要调用 get() 方法取出容器内的数据。
     */
    public static void reduceWithLambda() {
        List<Integer> list = Arrays.asList(1, 6, 2, 3, 5, 4);
        System.out.println(list.stream().reduce((sum, num) -> sum + num).get());
    }

    /**
     * 10、collection 基于目标集合的元素生成新集合
     * 从目标集合中取出所有的奇数生成一个新的集合，具体操作如下所示
     */
    public static void collectionWithLambda() {
        List<Integer> list = Arrays.asList(1, 6, 2, 3, 5, 4);
        List<Integer> list2 = list.stream()
                .filter(num -> num % 2 != 0)
                .collect(Collectors.toList());
        list2.forEach(num -> System.out.println(num));
    }
}
