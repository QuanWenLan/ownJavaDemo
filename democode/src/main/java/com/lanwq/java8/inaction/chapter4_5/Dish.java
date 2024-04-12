package com.lanwq.java8.inaction.chapter4_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Lan
 * @createTime 2023-10-27  09:22
 **/
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    public enum Type {MEAT, FISH, OTHER}

    @Override
    public String toString() {
        return name;
    }

    public static final List<Dish> menu =
            Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
                    new Dish("beef", false, 700, Dish.Type.MEAT),
                    new Dish("chicken", false, 400, Dish.Type.MEAT),
                    new Dish("french fries", true, 530, Dish.Type.OTHER),
                    new Dish("rice", true, 350, Dish.Type.OTHER),
                    new Dish("season fruit", true, 120, Dish.Type.OTHER),
                    new Dish("pizza", true, 550, Dish.Type.OTHER),
                    new Dish("prawns", false, 400, Dish.Type.FISH),
                    new Dish("salmon", false, 450, Dish.Type.FISH));

    public static void main(String[] args) {
        // 中间操作与终端操作
        List<String> names = menu.stream()
                .filter((Dish d) -> {
                    System.out.println("filtering " + d.getName());
                    return d.getCalories() > 300;
                }) // 中间操作（可以连接起来的操作）
                .map(d -> {
                    System.out.println("mapping " + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(Collectors.toList()); // 终端操作（关闭流）
        System.out.println(names);
        // 尽管filter和map是两个独立的操作，但它们合并到同一次遍历中了（我们把这种技术叫作循环合并）。
        /*
        输出：能看到它们是在同一次遍历中
        filtering pork
        mapping pork
        filtering beef
        mapping beef
        filtering chicken
        mapping chicken
        [pork, beef, chicken]
         */

        // 筛选和切片
        // 1 谓词
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());

        // 2 筛选
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        // 4 跳过 skip 返回一个扔掉了前n个元素的流， 如果流中元素不足n个，则返回一个空流。
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());
        System.out.println(dishes);


        // 映射
        // 映射 map 接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        List<String> words1 = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words1.stream()
                .map(String::length)
                .collect(Collectors.toList());

        // 流的扁平化 flatMap
        ArrayList<String> words = new ArrayList<>();
        words.add("hello");
        words.add("world");
        List<String> uniqueCharacters = words.stream()
                .map(w -> w.split("")) // 将每个单词转换为由其字母构成的数组
                .flatMap(Arrays::stream)  // 将各个生成流扁平化为单个流
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueCharacters);
        // 使用flatMap方法的效果是，各个数组并不是分别映射成一个流，而是映射成流的内容。所
        //有使用map(Arrays::stream)时生成的单个流都被合并起来，即扁平化为一个流。

        // example, 给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
        // 你可以使用两个map来迭代这两个列表，并生成数对。但这样会返回一个 Stream- <Stream<Integer[]>>。你需要让生成的流扁平化，以得到一个Stream<Integer[]>
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .map(j -> new int[]{i, j})
                )
                .collect(Collectors.toList());
        for (int[] pair : pairs) {
            System.out.println(Arrays.toString(pair));
        }
        System.out.println("==============");
        //  如何扩展前一个例子，只返回总和能被3整除的数对呢？例如(2, 4)和(3, 3)是可以的
        List<int[]> pairs2 = numbers1.stream()
                .flatMap(i ->
                        numbers2.stream()
                                .filter(j -> (i + j) % 3 == 0)
                                .map(j -> new int[]{i, j})
                )
                .collect(Collectors.toList());
        for (int[] pair : pairs2) {
            System.out.println(Arrays.toString(pair));
        }


        // ================= 查找和匹配
        // anyMatch 方法返回一个boolean，因此是一个终端操作。
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }
        // allMatch
        boolean isHealthy = menu.stream()
                .allMatch(d -> d.getCalories() < 1000);
        // noneMatch 两个互补
        boolean isHealthy2 = menu.stream()
                .noneMatch(d -> d.getCalories() >= 1000);

        // 查找
        Optional<Dish> dish = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        dish.ifPresent(d -> System.out.println(d.getName()));

        // 查找第一个元素
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                        .map(x -> x * x)
                        .filter(x -> x % 3 == 0)
                        .findFirst(); // 9
    }
}
