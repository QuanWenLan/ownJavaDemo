package com.lanwq.java8.lambda;

import com.alibaba.fastjson.JSON;
import com.lanwq.java8.lambda.data.Student;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Vin lan
 * @className LambdaDemo
 * @description TODO 使用lambda的一些案例
 * @createTime 2020-10-10  16:19
 **/
public class LambdaDemo {
    public static void main(String[] args) {
        System.out.println("======replaceAnonymousInnerClass=======");
        replaceAnonymousInnerClass();
        System.out.println("======traversalCollection=======");
        traversalCollection();
        System.out.println("======filterWithLambda=======");
        filterWithLambda();
        System.out.println("======filterWithMultiplyPredicate=======");
        filterWithMultiplyPredicate();
        System.out.println("======limitWithLambda=======");
        limitWithLambda();
        System.out.println("======lambdaWithSorted=======");
        lambdaWithSorted();
        System.out.println("======maxAndMin=======");
        maxAndMin();
        System.out.println("======mapWithLambda=======");
        mapWithLambda();
        System.out.println("======reduceWithLambda=======");
        reduceWithLambda();
        System.out.println("======collectionWithLambda=======");
        collectionWithLambda();
        System.out.println("======groupByWithLambda=======");
        groupBy();
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

    /**
     * group by
     */
    public static void groupBy() {
        Student student1 = new Student("7", "701", "张三", 16, "北京", 78, 90);
        Student student2 = new Student("7", "700", "李四", 17, "北京", 78, 90);
        Student student3 = new Student("7", "703", "王五", 16, "上海", 78, 90);
        Student student4 = new Student("7", "701", "赵六", 16, "上海", 78, 90);
        Student student5 = new Student("7", "700", "钱七", 18, "", 78, 90);
        Student student6 = new Student("7", "701", "老八", 17, "", 78, 90);
        // 这是一个高二年级的成绩单
        List<Student> students = Stream.of(student1, student2, student3, student4, student5, student6).collect(Collectors.toList());
        // 1 按照班级分组
        System.out.println("按照班级分组");
        Map<String, List<Student>> collect1 = students.stream().collect(Collectors.groupingBy(Student::getClassNumber));
        System.out.println(JSON.toJSONString(collect1));
        //{"700":[{"age":17,"chainessScores":90,"classNumber":"700","mathScores":78,"name":"李四"},{"age":18,"chainessScores":90,"classNumber":"700","mathScores":78,"name":"钱七"}],
        //"701":[{"age":16,"chainessScores":90,"classNumber":"701","mathScores":78,"name":"张三"},{"age":16,"chainessScores":90,"classNumber":"701","mathScores":78,"name":"赵六"},{"age":17,"chainessScores":90,"classNumber":"701","mathScores":78,"name":"老八"}],
        //"703":[{"age":16,"chainessScores":90,"classNumber":"703","mathScores":78,"name":"王五"}]}

        // 2 按照班级分组得到每个班级的同学姓名
        System.out.println("按照班级分组得到每个班级的同学姓名");
        Map<String, List<String>> collect2 = students.stream().collect(
                Collectors.groupingBy(Student::getClassNumber, Collectors.mapping(Student::getName, Collectors.toList())));
        System.out.println(JSON.toJSONString(collect2));
        //{"700":["李四","钱七"],"701":["张三","赵六","老八"],"703":["王五"]}

        // 3 统计每个班级人数
        System.out.println("统计每个班级人数");
        Map<String, Long> collect3 = students.stream().collect(Collectors.groupingBy(Student::getClassNumber, Collectors.counting()));
        System.out.println(JSON.toJSONString(collect3));
        //{"700":2,"701":3,"703":1}

        // 4 求每个班级的数学平均成绩
        System.out.println("求每个班级的数学平均成绩");
        Map<String, Double> collect4 = students.stream().collect(
                Collectors.groupingBy(Student::getClassNumber, Collectors.averagingDouble(Student::getMathScores)));
        System.out.println(JSON.toJSONString(collect4));
        //{"700":65.0,"701":61.0,"703":82.0}

        // 5 按班级分组求每个同学的总成绩
        System.out.println("按班级分组求每个同学的总成绩");
        Map<String, Map<String, Integer>> collect5 = students.stream().collect(
                Collectors.groupingBy(Student::getClassNumber, Collectors.toMap(Student::getName, student -> student.getMathScores() + student.getChineseScores())));
        System.out.println(JSON.toJSONString(collect5));
        //{"700":{"钱七":150,"李四":160},"701":{"张三":168,"老八":148,"赵六":137},"703":{"王五":172}}

        // 6 嵌套分组，先按班级分组，再按年龄分组
        System.out.println("嵌套分组，先按班级分组，再按年龄分组");
        Map<String, Map<Integer, List<Student>>> collect6 = students.stream().collect(
                Collectors.groupingBy(Student::getClassNumber, Collectors.groupingBy(Student::getAge)));
        System.out.println(JSON.toJSONString(collect6));

        // 7 分组后得到一个线程安全的ConcurrentMap
        System.out.println("分组后得到一个线程安全的ConcurrentMap");
        ConcurrentMap<String, List<Student>> collect7 = students.stream().collect(
                Collectors.groupingByConcurrent(Student::getClassNumber));
        System.out.println(JSON.toJSONString(collect7));

        // 8根据年龄分组并小到大排序,TreeMap默认为按照key升序
        System.out.println("根据年龄分组并小到大排序,TreeMap默认为按照key升序");
        TreeMap<Integer, List<String>> collect8 = students.stream().collect(
                Collectors.groupingBy(Student::getAge, TreeMap::new, Collectors.mapping(Student::getName, Collectors.toList())));
        System.out.println(JSON.toJSONString(collect8));
        //{16:["张三","王五","赵六"],17:["李四","老八"],18:["钱七"]}

        // 9 根据年龄分组并大到小排序,因为TreeMap默认为按照key升序，所以排完顺序再反转一下就OK了
        System.out.println("根据年龄分组并大到小排序,TreeMap默认为按照key升序");
        TreeMap<Integer, List<String>> collect9 = students.stream().collect(
                Collectors.groupingBy(Student::getAge, TreeMap::new, Collectors.mapping(Student::getName, Collectors.toList())));
        Map<Integer, List<String>> collect10 = collect9.descendingMap();
        System.out.println(JSON.toJSONString(collect10));
        //{18:["钱七"],17:["李四","老八"],16:["张三","王五","赵六"]}
    }


}
